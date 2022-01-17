package com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers

import android.content.Context
import com.beleavemebe.solevarnya.presentation.di.Injector
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
            delay(2000)
            val newTeacher = Injector.createTeacher()
            val newItemIndex = Injector.addTeacher(newTeacher)
            view?.addTeacher(newTeacher)
            view?.onTeacherAdded(newItemIndex)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
    }
}
