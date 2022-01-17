package com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers

import android.content.Context
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseView

interface TeachersContract {
    interface Presenter : BasePresenter {
        fun onAddTeacherClicked(context: Context)
    }

    interface View : BaseView<Teacher, Presenter> {
        fun addTeacher(teacher: Teacher)
        fun onTeacherAdded(at: Int)
    }
}
