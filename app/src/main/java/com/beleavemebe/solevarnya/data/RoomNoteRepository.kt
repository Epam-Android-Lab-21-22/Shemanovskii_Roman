package com.beleavemebe.solevarnya.data

import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import java.io.IOException

class RoomNoteRepository : NoteRepository {
    override suspend fun fetchAll(): List<Note> {
        throw IOException()
    }

    override suspend fun add(note: Note) {
        throw IOException()
    }
}
