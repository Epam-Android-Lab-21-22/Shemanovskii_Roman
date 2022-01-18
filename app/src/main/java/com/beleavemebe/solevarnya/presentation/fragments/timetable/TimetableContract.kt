package com.beleavemebe.solevarnya.presentation.fragments.timetable

import com.beleavemebe.solevarnya.core.domain.Lesson
import com.beleavemebe.solevarnya.presentation.fragments.BaseListView
import com.beleavemebe.solevarnya.presentation.fragments.BaseListPresenter

interface TimetableContract {
    interface Presenter : BaseListPresenter {
        fun onLoadMoreClicked()
    }

    interface View : BaseListView<Lesson> {
        fun showDisappointmentToast()
    }
}
