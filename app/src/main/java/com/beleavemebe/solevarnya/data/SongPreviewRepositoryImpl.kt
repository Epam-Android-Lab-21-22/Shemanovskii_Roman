package com.beleavemebe.solevarnya.data

import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository
import javax.inject.Inject

class SongPreviewRepositoryImpl @Inject constructor(
    private val songDataSource: SongDataSource,
) : SongPreviewRepository {
    override suspend fun searchSongPreviews(query: String): List<SongPreview> {
        return songDataSource.getSongs().map(SongDetails::toPreview)
    }
}
