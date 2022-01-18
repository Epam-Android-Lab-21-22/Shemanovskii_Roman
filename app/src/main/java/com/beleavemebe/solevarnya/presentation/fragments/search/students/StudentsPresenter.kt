package com.beleavemebe.solevarnya.presentation.fragments.search.students

import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.framework.di.Injector
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentsPresenter(
    private var view: StudentsContract.View?,
) : StudentsContract.Presenter() {
    override fun onRecyclerReady() {
        val students = Injector.getStudents()
        view?.setContent(students)
    }

    override fun onMoveStudent(from: Int, to: Int) {
        view?.swapStudents(from, to)
        Injector.swapStudents(from, to)
    }

    override fun onRemoveStudent(student: Student) {
        presenterScope.launch {
            view?.showLoading()
            delay(1000)
            view?.expelStudent(student)
            Injector.removeStudent(student)
            view?.hideLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
    }
}
