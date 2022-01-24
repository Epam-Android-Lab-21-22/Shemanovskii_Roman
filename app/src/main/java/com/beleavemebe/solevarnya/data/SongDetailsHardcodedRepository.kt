package com.beleavemebe.solevarnya.data

import android.content.res.AssetManager
import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

object SongDetailsHardcodedRepository : SongDetailsRepository {
    override suspend fun getSongDetails(id: String): SongDetails {
        return allSongs.first { it.id == id }
    }

    override suspend fun searchSongs(query: String): List<SongDetails> {
        return if (query.isBlank())
            allSongs
        else
            allSongs.filter { it.mentions(query) }
    }

    private lateinit var allSongs: List<SongDetails>

    suspend fun init(assetManager: AssetManager) =
        withContext(Dispatchers.IO) {
            allSongs = deserializeSongs(assetManager).shuffled()
        }

    private fun deserializeSongs(assetManager: AssetManager): List<SongDetails> {
        val stream = assetManager.open("songs.json")
        return Json.decodeFromStream(stream)
    }
}
