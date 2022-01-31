package com.beleavemebe.solevarnya.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beleavemebe.solevarnya.data.model.NoteDto

@Database(entities = [NoteDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
