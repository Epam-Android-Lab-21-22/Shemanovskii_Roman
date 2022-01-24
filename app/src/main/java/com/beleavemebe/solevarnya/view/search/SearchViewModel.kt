package com.beleavemebe.solevarnya.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.beleavemebe.solevarnya.data.SongDetailsHardcodedRepository
import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.domain.repository.SongPreviewRepository
import com.beleavemebe.solevarnya.domain.usecase.SearchSongPreviews
import com.beleavemebe.solevarnya.domain.usecase.SearchSongs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest

class SearchViewModel : ViewModel(), ISearchViewModel {
    private val searchSongPreviews =
        SearchSongPreviews(SongPreviewRepository(SearchSongs(SongDetailsHardcodedRepository))) // lol xddd

    private var mutableQuery = MutableStateFlow("")
    private var mutableShouldShowLoading = MutableStateFlow(false)

    override val songs =
        mutableQuery
            .mapLatest(::launchSearch)
            .asLiveData()

    override val shouldShowLoading =
        mutableShouldShowLoading
            .asLiveData()

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
