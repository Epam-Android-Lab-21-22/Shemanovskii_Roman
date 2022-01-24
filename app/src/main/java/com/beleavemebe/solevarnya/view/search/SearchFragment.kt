package com.beleavemebe.solevarnya.view.search

import android.content.Context
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
import com.beleavemebe.solevarnya.view.appComponent
import com.beleavemebe.solevarnya.view.util.doOnQueryChanged
import javax.inject.Inject

private typealias Binding = FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(Binding::bind)
    private val adapter = SearchAdapter(::navigateToSongDetails)
    @Inject lateinit var vmFactory: SearchViewModelFactory
    private val viewModel: ISearchViewModel by viewModels<SearchViewModel> {
        vmFactory
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initToolbar()
        binding.initRecyclerView()
        subscribeToViewModel()
    }

    private fun Binding.initToolbar() {
        toolbar.setupWithNavController(findNavController())
        toolbar.inflateMenu(R.menu.fragment_search)
        initSearchAction(toolbar.menu)
    }

    private fun Binding.initRecyclerView() {
        rvSongs.adapter = adapter
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
