package com.beleavemebe.solevarnya.domain.repository

import com.beleavemebe.solevarnya.domain.model.Note

interface NoteRepository {
    suspend fun fetchAll(): List<Note>
    suspend fun add(note: Note)
}
