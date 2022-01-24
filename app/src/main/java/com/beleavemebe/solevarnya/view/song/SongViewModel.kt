package com.beleavemebe.solevarnya.view.song

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beleavemebe.solevarnya.data.SongDetailsHardcodedRepository
import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.domain.usecase.GetSongDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class SongViewModel : ViewModel(), ISongViewModel {
    private val getSongDetails = GetSongDetails(SongDetailsHardcodedRepository)

    private val mutableSongId = MutableStateFlow<String?>(null)
    private val mutableShouldShowLoading = MutableLiveData(true)

    override val songDetails: LiveData<SongDetails> =
        mutableSongId.mapNotNull { songId ->
            songId?.let { id ->
                val result = getSongDetails(id)
                mutableShouldShowLoading.value = false
                result
            }
        }.asLiveData()


    override val shouldShowLoading: LiveData<Boolean> =
        mutableShouldShowLoading

    override fun submitSongId(id: String) {
        mutableSongId.value = id
    }
}
