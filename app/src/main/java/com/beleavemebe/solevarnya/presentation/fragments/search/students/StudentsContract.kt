package com.beleavemebe.solevarnya.presentation.fragments.search.students

import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.presentation.fragments.BaseListView
import com.beleavemebe.solevarnya.presentation.fragments.CoroutinePresenter

interface StudentsContract {
    abstract class Presenter : CoroutinePresenter() {
        abstract fun onMoveStudent(from: Int, to: Int)
        abstract fun onRemoveStudent(student: Student)
    }

    interface View : BaseListView<Student> {
        fun swapStudents(from: Int, to: Int)
        fun expelStudent(student: Student)
        fun showLoading()
        fun hideLoading()
    }
}

