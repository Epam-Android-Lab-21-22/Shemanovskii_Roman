package com.beleavemebe.solevarnya.view.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentSearchBinding
import com.beleavemebe.solevarnya.domain.model.SongPreview
import com.beleavemebe.solevarnya.view.util.doOnQueryChanged

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: ISearchViewModel by viewModels<SearchViewModel>()
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val adapter = SearchAdapter(::navigateToSongDetails)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecyclerView()
        subscribeToViewModel()
    }

    private fun initToolbar() {
        binding.toolbar.setupWithNavController(findNavController())
        binding.toolbar.inflateMenu(R.menu.fragment_search)
        initSearchAction(binding.toolbar.menu)
    }

    private fun initRecyclerView() {
        binding.rvSongs.adapter = adapter
    }

    private fun subscribeToViewModel() {
        viewModel.songs.observe(viewLifecycleOwner, adapter::setContent)
        viewModel.shouldShowLoading.observe(viewLifecycleOwner, ::setLoadingVisible)
    }

    private fun setLoadingVisible(visible: Boolean) {
        binding.rvSongs.isVisible = !visible
        binding.progressCircular.isVisible = visible
    }

    private fun initSearchAction(menu: Menu) {
        val searchView = menu.findItem(R.id.item_search_view).actionView as SearchView
        searchView.doOnQueryChanged(viewModel::onQueryTextChanged)
    }

    private fun navigateToSongDetails(songPreview: SongPreview) {
        findNavController()
            .navigate(
                SearchFragmentDirections.toSongDetails(songPreview.id, songPreview.title)
            )
    }
}
