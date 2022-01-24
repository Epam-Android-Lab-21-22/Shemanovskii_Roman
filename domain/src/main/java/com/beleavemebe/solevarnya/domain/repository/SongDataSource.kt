package com.beleavemebe.solevarnya.domain.repository

import com.beleavemebe.solevarnya.domain.model.SongDetails

interface SongDataSource {
    suspend fun getSongs(): List<SongDetails>
}
