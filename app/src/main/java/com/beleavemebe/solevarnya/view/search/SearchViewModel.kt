package com.beleavemebe.solevarnya.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.domain.usecase.SearchSongPreviews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest

class SearchViewModel(
    private val searchSongPreviews: SearchSongPreviews,
) : ViewModel(), ISearchViewModel {
    private var mutableQuery = MutableStateFlow("")
    private var mutableShouldShowLoading = MutableLiveData(false)

    override val songs =
        mutableQuery
            .mapLatest(::launchSearch)
            .asLiveData()

    override val shouldShowLoading =
        mutableShouldShowLoading

    override fun onQueryTextChanged(query: String) {
        mutableQuery.value = query
    }

    private suspend fun launchSearch(query: String): List<SongPreview> {
        mutableShouldShowLoading.value = true
        val result = searchSongPreviews(query)
        mutableShouldShowLoading.value = false
        return result
    }

}
