package com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers

import android.content.Context
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseListView

interface TeachersContract {
    interface Presenter : BasePresenter {
        fun onAddTeacherClicked(context: Context)
    }

    interface View : BaseListView<Teacher> {
        fun addTeacher(teacher: Teacher)
        fun onTeacherAdded(at: Int)
    }
}
