package com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.presentation.ui.fragments.Constants.ITEM_MARGIN
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.BaseRecyclerFragmentWithPresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.decoration.GridMarginDecoration

class TeachersFragment :
    BaseRecyclerFragmentWithPresenter<Teacher, TeachersContract.Presenter>(),
    TeachersContract.View
{
    override val presenter: TeachersContract.Presenter = TeachersPresenter(this)

    override fun initRecycler(rv: RecyclerView) {
        with(rv) {
            adapter = TeacherAdapter {
                presenter.onAddTeacherClicked(requireContext())
            }
            layoutManager = StaggeredGridLayoutManager(
                TEACHER_GRID_COLUMNS, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridMarginDecoration(ITEM_MARGIN))
        }
    }

    override fun addTeacher(teacher: Teacher) = addItem(teacher)

    override fun onTeacherAdded(at: Int) = binding.rvMain.smoothScrollToPosition(at + 1)

    override fun showLoading() = showLoadingBar()

    override fun hideLoading() = hideLoadingBar()

    companion object {
        private const val TEACHER_GRID_COLUMNS = 2
    }
}
