package com.beleavemebe.solevarnya.data

import android.content.Context
import android.os.Environment
import com.beleavemebe.solevarnya.domain.model.Note
import java.io.File
import java.io.IOException

private const val FILE_NAME_NOTES = "notes_external.json"

class ExternalStorageNoteRepository(context: Context) : JsonFileNoteRepository() {

    private val externalDir = context.getExternalFilesDir(null)

    override val jsonFile = externalDir?.run { File("$path/$FILE_NAME_NOTES") }

    override suspend fun add(note: Note) {
        if (canWrite().not()) throw IOException("External storage unavailable")
        else super.add(note)
    }

    private fun canWrite(): Boolean =
        if (externalDir == null) {
            false
        } else {
            Environment.getExternalStorageState(externalDir) == Environment.MEDIA_MOUNTED
        }
}
