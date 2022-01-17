package com.beleavemebe.solevarnya.presentation.ui.fragments.search.students

import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.presentation.di.Injector

class StudentsPresenter(
    private var view: StudentsContract.View?,
) : StudentsContract.Presenter {
    override fun onRecyclerReady() {
        val students = Injector.getStudents()
        view?.setContent(students)
    }

    override fun onMoveStudent(from: Int, to: Int) {
        view?.swapStudents(from, to)
        Injector.swapStudents(from, to)
    }

    override fun onRemoveStudent(student: Student) {
        view?.expelStudent(student)
        Injector.removeStudent(student)
    }

    override fun onDestroy() {
        view = null
    }
}
