@file:Suppress("unchecked_cast")

package com.beleavemebe.solevarnya.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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

    private var items = mutableListOf<T>()

    private val adapter: ListAdapter<T, *>
        get() = (binding.rvMain.adapter as? ListAdapter<T, *>)
            ?: illegalState("RecyclerFragment subclasses are expected to use ListAdapter")

    abstract fun fetchItems(): List<T>
    abstract fun initRecycler(rv: RecyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = fetchItems().toMutableList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(binding.rvMain)
        refreshItems()
    }

    private fun refreshItems() {
        // ListAdapter wants a fresh reference on every list update to run diff check,
        // so we make a copy with `.toList()` in the following line https://stackoverflow.com/q/49726385
        val list = items.toList()
        adapter.submitList(list)
        binding.tvListIsEmpty.isVisible = list.isEmpty()
    }

    protected fun removeItem(item: T) {
        items.remove(item)
        refreshItems()
    }

    protected fun addItem(item: T) {
        items.add(item)
        refreshItems()
    }

    protected fun swapItems(from: Int, to: Int) {
        items.swap(from, to)
        refreshItems()
    }
}
