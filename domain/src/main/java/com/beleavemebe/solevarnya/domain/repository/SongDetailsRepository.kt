package com.beleavemebe.solevarnya.domain.repository

import com.beleavemebe.solevarnya.domain.model.SongDetails

interface SongDetailsRepository {
    suspend fun getSongDetails(id: String): SongDetails
}
