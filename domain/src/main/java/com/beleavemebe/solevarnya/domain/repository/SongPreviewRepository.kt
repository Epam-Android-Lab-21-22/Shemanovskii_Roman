package com.beleavemebe.solevarnya.domain.repository

import com.beleavemebe.solevarnya.domain.model.SongPreview

interface SongPreviewRepository {
    suspend fun searchSongPreviews(query: String): List<SongPreview>
}
