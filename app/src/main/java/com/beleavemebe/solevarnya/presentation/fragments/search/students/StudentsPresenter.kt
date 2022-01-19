package com.beleavemebe.solevarnya.presentation.fragments.search.students

import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.core.usecase.GetStudents
import com.beleavemebe.solevarnya.core.usecase.RemoveStudent
import com.beleavemebe.solevarnya.core.usecase.SwapStudents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentsPresenter(
    private var view: StudentsContract.View?,
    private val getStudents: GetStudents,
    private val swapStudents: SwapStudents,
    private val removeStudent: RemoveStudent,
) : StudentsContract.Presenter() {
    override fun onRecyclerReady() {
        val students = getStudents()
        view?.setContent(students)
    }

    override fun onMoveStudent(from: Int, to: Int) {
        view?.swapStudents(from, to)
        swapStudents(from, to)
    }

    override fun onRemoveStudent(student: Student) {
        presenterScope.launch {
            view?.showLoading()
            delay(1000)
            view?.expelStudent(student)
            removeStudent(student)
            view?.hideLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
    }
}
