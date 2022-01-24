package com.beleavemebe.solevarnya.view.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemSongBinding
import com.beleavemebe.solevarnya.domain.model.SongPreview

class SearchAdapter(
    private val onSongClicked: (SongPreview) -> Unit,
) : RecyclerView.Adapter<SearchAdapter.SongViewHolder>() {
    private val items = mutableListOf<SongPreview>()

    @SuppressLint("NotifyDataSetChanged")
    fun setContent(content: List<SongPreview>) {
        items.clear()
        items += content
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSongBinding.inflate(inflater, parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = items[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int = items.size

    inner class SongViewHolder(
        private val binding: ListItemSongBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songPreview: SongPreview) {
            binding.tvTitle.text = songPreview.title
            binding.tvArtist.text = songPreview.artist

            val coverUrl = songPreview.coverUrl
            if (coverUrl != null) {
                binding.ivCover.load(coverUrl)
            } else {
                binding.ivCover.load(R.drawable.placeholder_vinyl)
            }

            binding.root.setOnClickListener {
                onSongClicked(songPreview)
            }
        }
    }
}
