package com.beleavemebe.solevarnya.presentation.fragments.timetable

import com.beleavemebe.solevarnya.framework.di.Injector

class TimetablePresenter(
    private var view: TimetableContract.View?,
) : TimetableContract.Presenter {
    override fun onRecyclerReady() {
        val lessons = Injector.getLessons()
        view?.setContent(lessons)
    }

    override fun onLoadMoreClicked() {
        view?.showDisappointmentToast()
    }

    override fun onDestroy() {
        view = null
    }
}
