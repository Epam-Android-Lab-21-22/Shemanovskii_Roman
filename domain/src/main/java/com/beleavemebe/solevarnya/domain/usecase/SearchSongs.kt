package com.beleavemebe.solevarnya.domain.usecase

import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository

class SearchSongs(private val songDetailsRepository: SongDetailsRepository) {
    suspend operator fun invoke(query: String): List<SongDetails> =
        songDetailsRepository.searchSongs(query)
}
