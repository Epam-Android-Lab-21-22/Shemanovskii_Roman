package com.beleavemebe.solevarnya.domain.usecase

import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository

class SearchSongPreviews(private val songsPreviewRepository: SongPreviewRepository) {
    suspend operator fun invoke(query: String): List<SongPreview> =
        songsPreviewRepository.searchSongPreviews(query)
}
