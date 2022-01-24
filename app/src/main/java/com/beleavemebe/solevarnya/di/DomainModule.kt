package com.beleavemebe.solevarnya.di

import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository
import com.beleavemebe.solevarnya.domain.usecase.GetSongDetails
import com.beleavemebe.solevarnya.domain.usecase.SearchSongPreviews
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideSearchSongPreviews(
        songPreviewRepository: SongPreviewRepository,
    ): SearchSongPreviews {
        return SearchSongPreviews(songPreviewRepository)
    }

    @Provides
    fun provideGetSongDetails(
        songDetailsRepository: SongDetailsRepository,
    ): GetSongDetails {
        return GetSongDetails(songDetailsRepository)
    }
}
