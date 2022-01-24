package com.beleavemebe.solevarnya.view.search

import androidx.lifecycle.LiveData
import com.beleavemebe.solevarnya.domain.model.SongPreview

interface ISearchViewModel {
    val songs: LiveData<List<SongPreview>>
    val shouldShowLoading: LiveData<Boolean>

    fun onQueryTextChanged(query: String)
}
