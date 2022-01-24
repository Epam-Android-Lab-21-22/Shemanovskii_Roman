package com.beleavemebe.solevarnya.domain.model

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SongDetails(
    val id: String = UUID.randomUUID().toString(),
    val artist: String = "",
    val title: String = "",
    val coverUrl: String? = null,
    val year: String = "",
    val album: String = "",
    val genre: String = "",
    val lyrics: String = "",
) {
    fun mentions(text: String): Boolean =
        artist.contains(text, true) || title.contains(text, true)

    fun toPreview(): SongPreview =
        SongPreview(id, artist, title, coverUrl)
}
