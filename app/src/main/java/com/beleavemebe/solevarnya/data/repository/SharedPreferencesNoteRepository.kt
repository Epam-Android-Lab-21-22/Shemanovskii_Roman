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

class SharedPreferencesNoteRepository(context: Context) : NoteRepository {
    private val prefs = context.getSharedPreferences(PREFS_NOTES, MODE_PRIVATE)
    private var cachedNotes: List<Note>? = null

    override suspend fun fetchAll(): List<Note> {
        return cachedNotes ?: deserialize()
    }

    override suspend fun add(note: Note) {
        val notes = deserialize() + note
        cachedNotes = notes
        serialize(notes)
    }

    private suspend fun deserialize(): List<Note> =
        withContext(Dispatchers.IO) {
            val noteJsonSet = prefs.getStringSet(PREF_KEY_NOTES, setOf())!!
            val notes = noteJsonSet.map { Json.decodeFromString<Note>(it) }
            cachedNotes = notes
            notes
        }

    private suspend fun serialize(notes: List<Note>) {
        withContext(Dispatchers.IO) {
            val noteJsonSet = notes.map(Json.Default::encodeToString).toSet()
            prefs.edit {
                putStringSet(PREF_KEY_NOTES, noteJsonSet)
            }
        }
    }
}
