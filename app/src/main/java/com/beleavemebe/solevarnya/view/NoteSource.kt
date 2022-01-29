package com.beleavemebe.solevarnya.view

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R

sealed class NoteSource(@StringRes val titleResId: Int) {
    object SharedPrefs : NoteSource(R.string.shared_preferences)
    object InternalStorage : NoteSource(R.string.internal_storage)
    object ExternalStorage : NoteSource(R.string.external_storage)
    object RoomDatabase : NoteSource(R.string.room_database)
}

val DATA_SOURCES = arrayOf(
    NoteSource.SharedPrefs,
    NoteSource.InternalStorage,
    NoteSource.ExternalStorage,
    NoteSource.RoomDatabase
)
