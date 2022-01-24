package com.beleavemebe.solevarnya.di

import com.beleavemebe.solevarnya.data.SongDetailsRepositoryImpl
import com.beleavemebe.solevarnya.data.SongPreviewRepositoryImpl
import com.beleavemebe.solevarnya.domain.repository.SongDetailsRepository
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindSongDetailsRepository(
        implementation: SongDetailsRepositoryImpl,
    ): SongDetailsRepository

    @Binds
    abstract fun bindSongPreviewRepository(
        implementation: SongPreviewRepositoryImpl,
    ): SongPreviewRepository
}
