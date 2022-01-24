package com.beleavemebe.solevarnya.view.song

import androidx.lifecycle.LiveData
import com.beleavemebe.solevarnya.domain.model.SongDetails

interface ISongViewModel {
    val songDetails: LiveData<SongDetails>
    val shouldShowLoading: LiveData<Boolean>

    fun submitSongId(id: String)
}
