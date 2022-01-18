package com.beleavemebe.solevarnya.presentation.fragments.search.subjects

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.core.domain.Subject
import com.beleavemebe.solevarnya.presentation.fragments.Constants.ITEM_MARGIN
import com.beleavemebe.solevarnya.presentation.fragments.search.BaseRecyclerFragmentWithPresenter
import com.beleavemebe.solevarnya.presentation.fragments.search.decoration.GridMarginDecoration

class SubjectsFragment :
    BaseRecyclerFragmentWithPresenter<Subject, SubjectsContract.Presenter>(),
    SubjectsContract.View
{
    override val presenter: SubjectsContract.Presenter = SubjectsPresenter(this)

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = SubjectsAdapter()
            layoutManager = GridLayoutManager(
                requireContext(),
                SUBJECTS_GRID_COLS,
                GridLayoutManager.VERTICAL,
                false
            ).apply {
                spanSizeLookup = configureSpanSizeLookup()
            }
            addItemDecoration(GridMarginDecoration(ITEM_MARGIN))
        }
    }

    private fun configureSpanSizeLookup(): GridLayoutManager.SpanSizeLookup =
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (itemAt(position).credits >= 5) {
                    SUBJECTS_GRID_COLS
                } else 1
            }
        }

    companion object {
        private const val SUBJECTS_GRID_COLS = 2
    }
}
