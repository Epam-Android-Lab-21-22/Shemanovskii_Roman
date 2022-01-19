package com.beleavemebe.solevarnya.presentation.fragments.search.teachers

import com.beleavemebe.solevarnya.core.usecase.AddTeacher
import com.beleavemebe.solevarnya.core.usecase.CreateTeacher
import com.beleavemebe.solevarnya.core.usecase.GetTeachers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TeachersPresenter(
    private var view: TeachersContract.View?,
    private val getTeachers: GetTeachers,
    private val createTeacher: CreateTeacher,
    private val addTeacher: AddTeacher,
) : TeachersContract.Presenter() {
    override fun onRecyclerReady() {
        val teachers = getTeachers()
        view?.setContent(teachers)
    }

    override fun onAddTeacherClicked() {
        presenterScope.launch {
            view?.showLoading()
            delay(2000)
            val newTeacher = createTeacher()
            val newItemIndex = addTeacher(newTeacher)
            view?.addTeacher(newTeacher)
            view?.onTeacherAdded(newItemIndex)
            view?.hideLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
    }
}
