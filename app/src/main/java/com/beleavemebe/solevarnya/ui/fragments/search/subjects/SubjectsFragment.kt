package com.beleavemebe.solevarnya.ui.fragments.search.subjects

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beleavemebe.solevarnya.repository.SubjectRepository
import com.beleavemebe.solevarnya.ui.fragments.search.RecyclerFragment
import javax.security.auth.Subject

private const val SUBJECTS_GRID_COLS = 3

class SubjectsFragment : RecyclerFragment<Subject>() {
    override fun fetchItems(): List<Subject> {
        return SubjectRepository.fetchAll()
    }

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = SubjectsAdapter()
            layoutManager = StaggeredGridLayoutManager(SUBJECTS_GRID_COLS, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
