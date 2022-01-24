package com.beleavemebe.solevarnya.data

import android.content.res.AssetManager
import com.beleavemebe.solevarnya.domain.model.SongDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class SongDataSource @Inject constructor(
    private val assetManager: AssetManager,
) {
    private var songs: List<SongDetails>? = null

    suspend fun getSongs(): List<SongDetails> =
        songs ?: initSongs()

    private suspend fun initSongs(): List<SongDetails> {
        val songs = withContext(Dispatchers.IO) { deserializeSongs() }
        this.songs = songs
        return songs
    }

    private fun deserializeSongs(): List<SongDetails> =
        try {
            val stream = assetManager.open("songs.json")
            Json.decodeFromStream(stream)
        } catch (e: Exception) {
            emptyList()
        }
}
