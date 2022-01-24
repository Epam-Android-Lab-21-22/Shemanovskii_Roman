package com.beleavemebe.solevarnya.view.song

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentSongBinding
import com.beleavemebe.solevarnya.domain.model.SongDetails
import com.beleavemebe.solevarnya.view.appComponent
import javax.inject.Inject

private typealias Binding = FragmentSongBinding

class SongFragment : Fragment(R.layout.fragment_song) {
    private val binding by viewBinding(Binding::bind)
    private val args by navArgs<SongFragmentArgs>()

    @Inject lateinit var vmFactory: SongViewModelFactory.Factory
    private val viewModel: ISongViewModel by viewModels {
        vmFactory.create(args.songId)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initToolbar()
        subscribeToViewModel()
    }

    private fun Binding.initToolbar() {
        collapsingToolbarLayout.setupWithNavController(toolbar, findNavController())
        toolbar.setNavigationIconTint(Color.WHITE)
    }

    private fun subscribeToViewModel() {
        viewModel.songDetails.observe(viewLifecycleOwner, ::renderSongDetails)
    }

    private fun renderSongDetails(songDetails: SongDetails) {
        binding.renderCover(songDetails.coverUrl)
        binding.renderSongInfo(songDetails)
    }

    private fun Binding.renderSongInfo(songDetails: SongDetails) {
        tvLyrics.text = songDetails.lyrics
        tvYearValue.text = songDetails.year
        tvAlbumValue.text = songDetails.album
        tvGenreValue.text = songDetails.genre
    }

    private fun Binding.renderCover(coverUrl: String?) {
        if (coverUrl != null) {
            ivCover.load(coverUrl)
        } else {
            ivCover.load(R.drawable.placeholder_vinyl)
        }
    }
}
