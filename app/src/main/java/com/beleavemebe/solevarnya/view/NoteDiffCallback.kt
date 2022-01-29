package com.beleavemebe.solevarnya.view

import androidx.recyclerview.widget.DiffUtil
import com.beleavemebe.solevarnya.domain.model.Note

class NoteDiffCallback(
    private val oldList: List<Note>,
    private val newList: List<Note>,
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

}
