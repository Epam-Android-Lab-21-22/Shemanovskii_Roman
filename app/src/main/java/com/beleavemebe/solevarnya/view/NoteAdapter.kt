package com.beleavemebe.solevarnya.view

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.databinding.ListItemNoteBinding
import com.beleavemebe.solevarnya.domain.model.Note

class NoteAdapter(
    private val listUpdateCallback: ListUpdateCallback,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val items = mutableListOf<Note>()

    fun submitNotes(notes: List<Note>) {
        val noteDiffCallback = NoteDiffCallback(items, notes)
        val diffResult = DiffUtil.calculateDiff(noteDiffCallback)
        diffResult.dispatchUpdatesTo(this)
        diffResult.dispatchUpdatesTo(listUpdateCallback)
        items.refillWith(notes)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = items[position]
        holder.bind(note)
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteItemContent.text = note.text
            binding.noteItemDatetime.text = DateFormat.format("EEE, d MMM yyyy HH:mm", note.date)
        }
    }
}

private fun <E> MutableList<E>.refillWith(items: List<E>) {
    clear()
    addAll(items)
}
