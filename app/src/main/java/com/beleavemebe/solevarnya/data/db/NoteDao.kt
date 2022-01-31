package com.beleavemebe.solevarnya.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beleavemebe.solevarnya.data.model.NoteDto

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun fetchAll(): List<NoteDto>

    @Insert
    suspend fun add(noteDto: NoteDto)
}
