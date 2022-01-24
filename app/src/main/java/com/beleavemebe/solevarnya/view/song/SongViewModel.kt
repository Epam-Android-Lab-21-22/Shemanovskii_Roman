package com.beleavemebe.solevarnya.view.song

import androidx.lifecycle.*
import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.usecase.GetSongDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class SongViewModel(
    private val songId: String,
    private val getSongDetails: GetSongDetails,
) : ViewModel(), ISongViewModel {
    override val songDetails: LiveData<SongDetails> =
        flow {
            emit(getSongDetails(songId))
        }
            .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
            .asLiveData()
}
