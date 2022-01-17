package com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers

import android.content.Context
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseListView
import com.beleavemebe.solevarnya.presentation.ui.fragments.CoroutinePresenter

interface TeachersContract {
    abstract class Presenter : CoroutinePresenter() {
        abstract fun onAddTeacherClicked(context: Context)
    }

    interface View : BaseListView<Teacher> {
        fun addTeacher(teacher: Teacher)
        fun onTeacherAdded(at: Int)
        fun showLoading()
        fun hideLoading()
    }
}
