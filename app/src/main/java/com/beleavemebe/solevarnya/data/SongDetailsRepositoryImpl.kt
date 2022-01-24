package com.beleavemebe.solevarnya.data

import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.repository.SongDataSource
import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository
import javax.inject.Inject

class SongDetailsRepositoryImpl @Inject constructor(
    private val songDataSource: SongDataSource,
) : SongDetailsRepository {
    override suspend fun getSongDetails(id: String): SongDetails {
        return songDataSource.getSongs().first { it.id == id }
    }
}
