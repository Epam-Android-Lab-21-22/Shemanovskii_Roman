package com.beleavemebe.solevarnya.domain.usecase

import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository

class GetSongDetails(private val songDetailsRepository: SongDetailsRepository) {
    suspend operator fun invoke(id: String): SongDetails =
        songDetailsRepository.getSongDetails(id)
}
