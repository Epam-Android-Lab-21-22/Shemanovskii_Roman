package com.beleavemebe.solevarnya.di

import android.app.Application
import com.beleavemebe.solevarnya.data.repository.ExternalStorageNoteRepository
import com.beleavemebe.solevarnya.data.repository.InternalStorageNoteRepository
import com.beleavemebe.solevarnya.data.repository.RoomNoteRepository
import com.beleavemebe.solevarnya.data.repository.SharedPreferencesNoteRepository

object ServiceLocator {
    lateinit var application: Application

    val sharedPreferencesNoteRepository by lazy { SharedPreferencesNoteRepository(application) }
    val internalStorageNoteRepository by lazy { InternalStorageNoteRepository(application) }
    val externalStorageNoteRepository by lazy { ExternalStorageNoteRepository(application) }
    val roomNoteRepository by lazy { RoomNoteRepository(application) }

    fun init(application: Application) {
        this.application = application
    }
}
