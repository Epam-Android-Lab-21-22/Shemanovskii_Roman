package com.beleavemebe.solevarnya.data.repository

import android.content.Context
import androidx.room.Room
import com.beleavemebe.solevarnya.data.db.AppDatabase
import com.beleavemebe.solevarnya.data.model.NoteDto
import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import java.util.*

class RoomNoteRepository(context: Context) : NoteRepository {
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "notes.db"
    ).build()

    private val dao = database.noteDao()

    override suspend fun fetchAll(): List<Note> =
        dao.fetchAll().map(NoteDto::toNote)

    override suspend fun add(note: Note) =
        dao.add(note.toDto())

}

private fun NoteDto.toNote(): Note {
    return Note(
        text = text,
        date = Date(date)
    )
}

private fun Note.toDto(): NoteDto {
    return NoteDto(
        id = 0,
        text = text,
        date = date.time
    )
}
