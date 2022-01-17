package com.beleavemebe.solevarnya.presentation.ui.fragments.timetable

import com.beleavemebe.solevarnya.core.domain.Lesson
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseListView
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter

interface TimetableContract {
    interface Presenter : BasePresenter {
        fun onLoadMoreClicked()
    }

    interface View : BaseListView<Lesson> {
        fun showDisappointmentToast()
    }
}
