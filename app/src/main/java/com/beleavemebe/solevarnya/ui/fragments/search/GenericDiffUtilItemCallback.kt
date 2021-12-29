package com.beleavemebe.solevarnya.ui.fragments.search

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

open class GenericDiffUtilItemCallback<T> : DiffUtil.ItemCallback<T>()  {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    @SuppressLint("DiffUtilEquals") // https://bit.ly/344EcOx
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) ?: (newItem === null)
    }
}
