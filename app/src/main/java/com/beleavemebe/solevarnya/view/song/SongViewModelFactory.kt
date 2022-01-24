package com.beleavemebe.solevarnya.view.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beleavemebe.solevarnya.domain.usecase.GetSongDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SongViewModelFactory @AssistedInject constructor(
    @Assisted("songId") private val songId: String,
    private val getSongDetails: GetSongDetails,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongViewModel(songId, getSongDetails) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("songId") songId: String): SongViewModelFactory
    }
}
