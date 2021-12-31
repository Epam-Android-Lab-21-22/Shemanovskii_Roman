package com.beleavemebe.solevarnya.ui.fragments.search.students

import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Student
import com.beleavemebe.solevarnya.repository.StudentRepository
import com.beleavemebe.solevarnya.ui.fragments.Constants.ITEM_MARGIN
import com.beleavemebe.solevarnya.ui.fragments.search.RecyclerFragment
import com.beleavemebe.solevarnya.ui.fragments.search.decoration.LinearMarginDecoration

class StudentsFragment : RecyclerFragment<Student>() {
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
            swapStudents(from, to)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            /* Nothing to do */
        }
    }

    override fun fetchItems(): List<Student> {
        return StudentRepository.fetchAll()
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
                        R.id.student_option_expel -> expelStudent(student)
                        else -> false
                    }
                }
            }.show()
    }

    private fun expelStudent(student: Student): Boolean {
        super.removeItem(student)
        StudentRepository.removeItem(student)
        return true
    }

    private fun swapStudents(from: Int, to: Int) {
        super.swapItems(from, to)
        StudentRepository.swap(from, to)
    }
}