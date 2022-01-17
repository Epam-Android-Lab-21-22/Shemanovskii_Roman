package com.beleavemebe.solevarnya.presentation.ui.fragments.timetable

import com.beleavemebe.solevarnya.presentation.di.Injector

class TimetablePresenter(
    private var view: TimetableContract.View?,
) : TimetableContract.Presenter {
    override fun onRecyclerReady() {
        val lessons = Injector.getLessons()
        view?.setContent(lessons)
    }

    override fun onDestroy() {
        view = null
    }
}
