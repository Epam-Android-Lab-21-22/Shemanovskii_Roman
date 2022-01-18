package com.beleavemebe.solevarnya.presentation.fragments.search.teachers

import android.content.Context
import com.beleavemebe.solevarnya.framework.di.Injector
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TeachersPresenter(
    private var view: TeachersContract.View?,
) : TeachersContract.Presenter() {
    override fun onRecyclerReady() {
        val teachers = Injector.getTeachers()
        view?.setContent(teachers)
    }

    override fun onAddTeacherClicked(context: Context) {
        presenterScope.launch {
            view?.showLoading()
            delay(2000)
            val newTeacher = Injector.createTeacher()
            val newItemIndex = Injector.addTeacher(newTeacher)
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
