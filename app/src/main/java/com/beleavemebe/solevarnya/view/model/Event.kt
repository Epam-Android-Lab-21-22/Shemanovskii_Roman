package com.beleavemebe.solevarnya.view.model

import com.beleavemebe.solevarnya.domain.model.Note

sealed class Event {
    data class SelectDataSource(val noteSource: NoteSource) : Event()
    data class AddNewNote(val note: Note) : Event()
}
