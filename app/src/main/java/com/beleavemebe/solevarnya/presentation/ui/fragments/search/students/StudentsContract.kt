package com.beleavemebe.solevarnya.presentation.ui.fragments.search.students

import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseView

interface StudentsContract {
    interface Presenter : BasePresenter {
        fun onMoveStudent(from: Int, to: Int)
        fun onRemoveStudent(student: Student)
    }

    interface View : BaseView<Student, Presenter> {
        fun swapStudents(from: Int, to: Int)
        fun expelStudent(student: Student)
    }
}

