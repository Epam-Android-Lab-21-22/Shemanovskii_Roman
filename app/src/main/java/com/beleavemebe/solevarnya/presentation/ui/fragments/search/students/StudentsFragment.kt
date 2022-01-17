package com.beleavemebe.solevarnya.presentation.ui.fragments.search.students

import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.presentation.ui.fragments.Constants.ITEM_MARGIN
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.BaseRecyclerFragmentWithPresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.decoration.LinearMarginDecoration

class StudentsFragment :
    BaseRecyclerFragmentWithPresenter<Student, StudentsContract.Presenter>(),
    StudentsContract.View
{
    override val presenter = StudentsPresenter(this)

    private val touchCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val from = viewHolder.absoluteAdapterPosition
            val to = target.absoluteAdapterPosition
            presenter.onMoveStudent(from, to)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            /* Nothing to do */
        }
    }

    override fun initRecycler(rv: RecyclerView) {
        with (rv) {
            adapter = StudentAdapter(::onMoreButtonClicked)
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(touchCallback).attachToRecyclerView(this)
            addItemDecoration(LinearMarginDecoration(ITEM_MARGIN))
        }
    }

    private fun onMoreButtonClicked(anchor: ImageButton, student: Student) {
        PopupMenu(requireContext(), anchor)
            .apply {
                menuInflater.inflate(R.menu.menu_student_options, menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.student_option_expel -> {
                            presenter.onRemoveStudent(student)
                            true
                        }
                        else -> false
                    }
                }
            }.show()
    }

    override fun expelStudent(student: Student) = removeItem(student)

    override fun swapStudents(from: Int, to: Int) = swapItems(from, to)

    override fun showLoading() = showLoadingBar()

    override fun hideLoading() = hideLoadingBar()
}
