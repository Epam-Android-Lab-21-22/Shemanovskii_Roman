package com.beleavemebe.solevarnya.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beleavemebe.solevarnya.domain.model.Note

interface IMainViewModel {
    val state: LiveData<State>

    fun onDataSourceChanged(dataSource: NoteSource)
    fun onAddNewNote()
}

sealed class State {
    object NoDataSourceSelected : State()
    object Loading : State()
    data class Error(val exception: NoteLoadingException) : State()
    data class DisplayingNotes(val notes: List<Note>) : State()
}

sealed class Event {
    data class SelectDataSource(val dataSource: NoteSource) : Event()
    data class AddNewNote(val note: Note) : Event()
}

sealed class NoteLoadingException : Exception() {
    object DataSourceUnavailable : NoteLoadingException()
}
