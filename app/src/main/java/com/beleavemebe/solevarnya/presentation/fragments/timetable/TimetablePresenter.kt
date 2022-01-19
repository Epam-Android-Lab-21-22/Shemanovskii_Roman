package com.beleavemebe.solevarnya.presentation.fragments.timetable

import com.beleavemebe.solevarnya.core.usecase.GetLessons

class TimetablePresenter(
    private var view: TimetableContract.View?,
    private val getLessons: GetLessons,
) : TimetableContract.Presenter {
    override fun onRecyclerReady() {
        val lessons = getLessons()
        view?.setContent(lessons)
    }

    override fun onLoadMoreClicked() {
        view?.showDisappointmentToast()
    }

    override fun onDestroy() {
        view = null
    }
}
