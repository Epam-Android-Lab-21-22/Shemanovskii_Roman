package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.repository.TeacherRepository
import com.beleavemebe.solevarnya.ui.fragments.search.ITEM_DEFAULT_MARGIN_PX
import com.beleavemebe.solevarnya.ui.fragments.search.RecyclerFragment
import com.beleavemebe.solevarnya.ui.fragments.search.decoration.GridMarginDecoration

private const val TEACHER_GRID_COLUMNS = 2

class TeachersFragment : RecyclerFragment<Teacher>() {
    override fun fetchItems(): List<Teacher> {
        return TeacherRepository.fetchAll()
    }

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = TeacherAdapter(::addTeacher)
            layoutManager = StaggeredGridLayoutManager(TEACHER_GRID_COLUMNS, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridMarginDecoration(ITEM_DEFAULT_MARGIN_PX))
        }
    }

    private fun addTeacher() {
        val newItem = TeacherRepository.getRandomTeacher(requireContext())
        TeacherRepository.addItem(newItem)
        super.addItem(newItem)
    }
}
