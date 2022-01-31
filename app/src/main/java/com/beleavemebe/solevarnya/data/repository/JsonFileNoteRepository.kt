@file:Suppress("BlockingMethodInNonBlockingContext")

package com.beleavemebe.solevarnya.data.repository

import com.beleavemebe.solevarnya.domain.model.Note
import com.beleavemebe.solevarnya.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

abstract class JsonFileNoteRepository : NoteRepository {
    abstract val jsonFile: File?

    private val inputStream: FileInputStream
        get() = jsonFile?.inputStream() ?: throw IOException("Could not read from input stream")

    private val outputStream: FileOutputStream
        get() = jsonFile?.outputStream() ?: throw IOException("Could not write to output stream")

    private var cachedNotes: List<Note>? = null

    override suspend fun fetchAll(): List<Note> {
        return cachedNotes ?: deserialize()
    }

    override suspend fun add(note: Note) {
        val notes = fetchAll() + note
        cachedNotes = notes
        serialize(notes)
    }

    private suspend fun serialize(notes: List<Note>) =
        withContext(Dispatchers.IO) {
            val notesJson = Json.encodeToString(notes)
            val bytes = notesJson.toByteArray()
            outputStream.write(bytes)
        }

    private suspend fun deserialize(): List<Note> =
        withContext(Dispatchers.IO) {
            try {
                val deserialized = Json.decodeFromStream<List<Note>>(inputStream)
                cachedNotes = deserialized
                deserialized
            } catch (e: SerializationException) {
                emptyList()
            }
        }
}
