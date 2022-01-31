package com.beleavemebe.solevarnya.view

import androidx.lifecycle.LiveData
import com.beleavemebe.solevarnya.view.model.NoteSource
import com.beleavemebe.solevarnya.view.model.State

interface IMainViewModel {
    val state: LiveData<State>

    fun onDataSourceChanged(noteSource: NoteSource)
    fun onAddNewNote()
}
