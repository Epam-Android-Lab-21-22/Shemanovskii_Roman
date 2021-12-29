@file:Suppress("unchecked_cast")

package com.beleavemebe.solevarnya.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentRecyclerBinding
import com.beleavemebe.solevarnya.util.illegalState
import com.beleavemebe.solevarnya.util.swap

const val ITEM_DEFAULT_MARGIN_PX = 20

abstract class RecyclerFragment<T> : Fragment(R.layout.fragment_recycler) {
    private val binding by viewBinding(FragmentRecyclerBinding::bind)

    private var items = listOf<T>()

    private val adapter: ListAdapter<T, *>
        get() = (binding.rvMain.adapter as? ListAdapter<T, *>)
            ?: illegalState("RecyclerFragment subclasses are expected to use ListAdapter")

    abstract fun fetchItems(): List<T>
    abstract fun initRecycler(rv: RecyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = fetchItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(binding.rvMain)
        refreshItems(items)
    }

    private fun refreshItems(
        items: Iterable<T>
    ) {
        // ListAdapter wants a fresh reference on every list update to run diff check, so we
        // accept Iterable just to make a copy in the following line https://stackoverflow.com/q/49726385
        adapter.submitList(items.toList())
    }

    protected fun removeItem(item: T) {
        val newItems = items - item
        items = newItems
        refreshItems(newItems)
    }

    protected fun swapItems(from: Int, to: Int) {
        val newItems = items.toMutableList()
        newItems.swap(from, to)
        refreshItems(newItems)
    }
}
