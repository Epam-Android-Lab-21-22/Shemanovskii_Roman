package com.beleavemebe.solevarnya.di

import android.app.Application
import android.content.Context
import com.beleavemebe.solevarnya.data.ExternalStorageNoteRepository
import com.beleavemebe.solevarnya.data.InternalStorageNoteRepository
import com.beleavemebe.solevarnya.data.RoomNoteRepository
import com.beleavemebe.solevarnya.data.SharedPreferencesNoteRepository

object ServiceLocator {
    lateinit var application: Application

    val sharedPreferencesNoteRepository by lazy { SharedPreferencesNoteRepository(application) }
    val internalStorageNoteRepository by lazy { InternalStorageNoteRepository(application) }
    val externalStorageNoteRepository by lazy { ExternalStorageNoteRepository(application) }
    val roomNoteRepository by lazy { RoomNoteRepository() }

    fun init(application: Application) {
        this.application = application
    }
}
