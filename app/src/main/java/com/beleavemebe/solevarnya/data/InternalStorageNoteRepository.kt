package com.beleavemebe.solevarnya.data

import android.content.Context
import java.io.File

private const val FILE_NAME_NOTES = "notes_internal.json"

class InternalStorageNoteRepository(context: Context) : JsonFileNoteRepository() {

    override val jsonFile = File("${context.filesDir.path}/$FILE_NAME_NOTES")

    init {
        jsonFile.createNewFile()
    }
}
