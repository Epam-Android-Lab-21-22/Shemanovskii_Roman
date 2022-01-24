package com.beleavemebe.solevarnya.domain.repository

import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.domain.usecase.SearchSongs

class SongPreviewRepository(private val searchSongs: SearchSongs) {
    suspend fun searchSongPreviews(query: String): List<SongPreview> =
        searchSongs(query).map(SongDetails::toPreview)
}
