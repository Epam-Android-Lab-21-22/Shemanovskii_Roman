package com.beleavemebe.solevarnya.di

import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository
import com.beleavemebe.solevarnya.domain.usecase.GetSongDetails
import com.beleavemebe.solevarnya.domain.usecase.SearchSongPreviews
import com.beleavemebe.solevarnya.domain.usecase.SearchSongs
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideSearchSongs(
        songDetailsRepository: SongDetailsRepository,
    ): SearchSongs {
        return SearchSongs(songDetailsRepository)
    }

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
