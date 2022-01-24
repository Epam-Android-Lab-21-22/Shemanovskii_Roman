package com.beleavemebe.solevarnya.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beleavemebe.solevarnya.domain.usecase.SearchSongPreviews
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val searchSongPreviews: SearchSongPreviews,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchSongPreviews) as T
    }
}
