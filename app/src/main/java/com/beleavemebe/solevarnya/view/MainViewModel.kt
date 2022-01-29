package com.beleavemebe.solevarnya.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beleavemebe.solevarnya.di.ServiceLocator
import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import com.github.javafaker.Faker
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.util.*

class MainViewModel : ViewModel(), IMainViewModel {
    private val faker = Faker()
    private var noteRepository: NoteRepository? = null
    private val mutableState = MutableLiveData<State>()

    init {
        mutableState.value = State.NoDataSourceSelected
    }

    override val state = mutableState

    override fun onDataSourceChanged(dataSource: NoteSource) {
        handleEvent(Event.SelectDataSource(dataSource))
    }

    override fun onAddNewNote() {
        val note = createRandomNote()
        handleEvent(Event.AddNewNote(note))
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MainViewModel", "Repo call raised an error", throwable)
        when (throwable) {
            is IOException -> {
                val exception = NoteLoadingException.DataSourceUnavailable
                mutableState.value = State.Error(exception)
            }
        }
    }

    private fun loadNotes(shouldShowLoading: Boolean) {
        viewModelScope.launch(exceptionHandler) {
            if (shouldShowLoading) {
                mutableState.value = State.Loading
            }

            val notes = noteRepository
                ?.fetchAll()
                ?.sortedBy { it.date }
                ?: emptyList()

            mutableState.value = State.DisplayingNotes(notes)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.SelectDataSource -> switchNoteDataSource(event.dataSource)
            is Event.AddNewNote -> addNote(event.note)
        }
    }

    private fun switchNoteDataSource(dataSource: NoteSource) {
        refreshRepositoryInstance(dataSource)
        loadNotes(shouldShowLoading = true)
    }

    private fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository?.add(note)
            loadNotes(shouldShowLoading = false)
        }
    }

    private fun refreshRepositoryInstance(dataSource: NoteSource) {
        noteRepository = when (dataSource) {
            NoteSource.SharedPrefs -> ServiceLocator.sharedPreferencesNoteRepository
            NoteSource.InternalStorage -> ServiceLocator.internalStorageNoteRepository
            NoteSource.ExternalStorage -> ServiceLocator.externalStorageNoteRepository
            NoteSource.RoomDatabase -> ServiceLocator.roomNoteRepository
        }
    }

    private fun createRandomNote(): Note {
        val quote = with(faker) {
            setOf(
                lebowski().quote(),
                twinPeaks().quote(),
                harryPotter().quote(),
                princessBride().quote(),
                gameOfThrones().quote(),
                backToTheFuture().quote(),
                hitchhikersGuideToTheGalaxy().quote(),
                howIMetYourMother().quote(),
                overwatch().quote(),
                friends().quote(),
                witcher().quote(),
                hobbit().quote(),
                yoda().quote(),
            ).random()
        }
        val date = Date()

        return Note(quote, date)
    }
}

