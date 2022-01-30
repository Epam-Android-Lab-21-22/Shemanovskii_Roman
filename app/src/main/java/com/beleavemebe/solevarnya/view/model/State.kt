package com.beleavemebe.solevarnya.view.model

import com.beleavemebe.solevarnya.domain.model.Note

sealed class State {
    object NoDataSourceSelected : State()
    object Loading : State()
    data class Error(val exception: NoteLoadingException) : State()
    data class DisplayingNotes(val notes: List<Note>) : State()
}
