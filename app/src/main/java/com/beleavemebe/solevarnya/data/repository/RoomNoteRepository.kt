package com.beleavemebe.solevarnya.data.repository

import android.content.Context
import androidx.room.Room
import com.beleavemebe.solevarnya.data.db.AppDatabase
import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import com.beleavemebe.solevarnya.mapper.NoteMapper
import java.io.IOException

class RoomNoteRepository(context: Context) : NoteRepository {
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "notes.db"
    ).build()

    private val dao = database.noteDao()

    override suspend fun fetchAll(): List<Note> =
        dao.fetchAll()
            .map {
                NoteMapper.noteDtoToNote(it)
            }

    override suspend fun add(note: Note) =
        dao.add(
            NoteMapper.noteToNoteDto(note)
        )

}
