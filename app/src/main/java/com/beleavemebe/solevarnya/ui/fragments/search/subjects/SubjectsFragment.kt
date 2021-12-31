package com.beleavemebe.solevarnya.ui.fragments.search.subjects

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.model.Subject
import com.beleavemebe.solevarnya.repository.SubjectRepository
import com.beleavemebe.solevarnya.ui.fragments.Constants.ITEM_MARGIN
import com.beleavemebe.solevarnya.ui.fragments.search.RecyclerFragment
import com.beleavemebe.solevarnya.ui.fragments.search.decoration.GridMarginDecoration

private const val SUBJECTS_GRID_COLS = 2

class SubjectsFragment : RecyclerFragment<Subject>() {
    override fun fetchItems(): List<Subject> {
        return SubjectRepository.fetchAll()
    }

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = SubjectsAdapter()
            layoutManager = GridLayoutManager(requireContext(), SUBJECTS_GRID_COLS, GridLayoutManager.VERTICAL, false)
                .apply {
                    spanSizeLookup = configureSpanSizeLookup()
                    addItemDecoration(GridMarginDecoration(ITEM_MARGIN))
                }
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
}
