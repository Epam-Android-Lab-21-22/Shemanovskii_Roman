package com.beleavemebe.solevarnya.data

import android.content.Context
import java.io.File

private const val FILE_NAME_NOTES = "notes_external.json"

class ExternalStorageNoteRepository(context: Context) : JsonFileNoteRepository() {

    private val externalStorageDir = context.getExternalFilesDir(null)

    override val jsonFile = externalStorageDir?.run {
        File("$path/$FILE_NAME_NOTES")
    }

    init {
        jsonFile?.createNewFile()
    }
}
