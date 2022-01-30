package com.beleavemebe.solevarnya.data.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val PREFS_NOTES = "notes"
private const val PREF_KEY_NOTES = "notes"

// TODO: 1/28/22 caching
class SharedPreferencesNoteRepository(context: Context) : NoteRepository {
    private val prefs = context.getSharedPreferences(PREFS_NOTES, MODE_PRIVATE)

    override suspend fun fetchAll(): List<Note> {
        return deserialize()
    }

    override suspend fun add(note: Note) {
        val notes = deserialize() + note
        serialize(notes)
    }

    private suspend fun deserialize(): List<Note> =
        withContext(Dispatchers.IO) {
            val noteJsonSet = prefs.getStringSet(PREF_KEY_NOTES, setOf())!!
            noteJsonSet.map(Json.Default::decodeFromString)
        }

    private suspend fun serialize(notes: List<Note>) {
        withContext(Dispatchers.IO) {
            val noteJsonSet = notes.map(Json.Default::encodeToString).toSet()
            prefs.edit(true) {
                putStringSet(PREF_KEY_NOTES, noteJsonSet)
            }
        }
    }
}
