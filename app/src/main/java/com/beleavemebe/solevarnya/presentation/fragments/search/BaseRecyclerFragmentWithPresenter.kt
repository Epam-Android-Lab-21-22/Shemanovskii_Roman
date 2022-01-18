@file:Suppress("unchecked_cast")

package com.beleavemebe.solevarnya.presentation.fragments.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentRecyclerBinding
import com.beleavemebe.solevarnya.presentation.fragments.BaseListPresenter
import com.beleavemebe.solevarnya.presentation.fragments.BaseListView
import com.beleavemebe.solevarnya.framework.util.illegalState
import com.beleavemebe.solevarnya.framework.util.swap

abstract class BaseRecyclerFragmentWithPresenter<T, P : BaseListPresenter> :
    Fragment(R.layout.fragment_recycler),
    BaseListView<T>
{
    abstract val presenter: P

    protected val binding by viewBinding(FragmentRecyclerBinding::bind)

    private var items = mutableListOf<T>()

    private val adapter: ListAdapter<T, *>
        get() = (binding.rvMain.adapter as? ListAdapter<T, *>)
            ?: illegalState("RecyclerFragment subclasses are expected to use ListAdapter")

    abstract fun initRecycler(rv: RecyclerView)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(binding.rvMain)
        presenter.onRecyclerReady()
        refreshItems()
    }

    private fun refreshItems() {
        // ListAdapter wants a fresh reference on every list update to run diff check,
        // so we make a copy with `.toList()` in the following line https://stackoverflow.com/q/49726385
        val list = items.toList()
        adapter.submitList(list)
        binding.tvListIsEmpty.isVisible = list.isEmpty()
    }

    override fun setContent(content: List<T>) = setItems(content)

    private fun setItems(content: List<T>) {
        items = content.toMutableList()
        refreshItems()
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

    protected fun itemAt(position: Int): T =
        items[position]

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    protected fun showLoadingBar() {
        binding.linearProgress.isVisible = true
    }

    protected fun hideLoadingBar() {
        binding.linearProgress.isVisible = false
    }
}
