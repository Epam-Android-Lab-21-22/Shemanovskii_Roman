package com.beleavemebe.solevarnya.view.song

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

class SongFragment : Fragment(R.layout.fragment_song) {
    private val viewModel: ISongViewModel by viewModels<SongViewModel>()
    private val binding by viewBinding(FragmentSongBinding::bind)
    private val args by navArgs<SongFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.submitSongId(args.songId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        subscribeToViewModel()
    }

    private fun initToolbar() {
        binding.collapsingToolbarLayout.setupWithNavController(binding.toolbar, findNavController())
        binding.toolbar.setNavigationIconTint(Color.WHITE)
    }

    private fun subscribeToViewModel() {
        viewModel.songDetails.observe(viewLifecycleOwner, ::renderSongDetails)
    }

    private fun renderSongDetails(songDetails: SongDetails) {
        renderCover(songDetails.coverUrl)
        renderSongInfo(songDetails)

    }

    private fun renderSongInfo(songDetails: SongDetails) {
        binding.tvLyrics.text = songDetails.lyrics
        binding.tvYearValue.text = songDetails.year
        binding.tvAlbumValue.text = songDetails.album
        binding.tvGenreValue.text = songDetails.genre
    }

    private fun renderCover(coverUrl: String?) {
        if (coverUrl != null) {
            binding.ivCover.load(coverUrl)
        } else {
            binding.ivCover.load(R.drawable.placeholder_vinyl)
        }
    }
}
