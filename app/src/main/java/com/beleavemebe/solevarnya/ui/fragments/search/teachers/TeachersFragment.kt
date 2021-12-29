package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.repository.TeacherRepository
import com.beleavemebe.solevarnya.ui.fragments.search.ITEM_DEFAULT_MARGIN_PX
import com.beleavemebe.solevarnya.ui.fragments.search.RecyclerFragment

private const val TEACHER_GRID_COLUMNS = 2

class TeachersFragment : RecyclerFragment<Teacher>() {
    override fun fetchItems(): List<Teacher> {
        return TeacherRepository.fetchAll()
    }

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = TeacherAdapter()
            layoutManager = GridLayoutManager(requireContext(), TEACHER_GRID_COLUMNS)
            addItemDecoration(GridMarginDecoration(ITEM_DEFAULT_MARGIN_PX))
        }
    }
}
