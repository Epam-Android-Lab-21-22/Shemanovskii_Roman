package com.beleavemebe.solevarnya.presentation.ui.fragments.search.subjects

import com.beleavemebe.solevarnya.core.domain.Subject
import com.beleavemebe.solevarnya.presentation.ui.fragments.BasePresenter
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseListView

interface SubjectsContract {
    interface Presenter : BasePresenter
    interface View : BaseListView<Subject>
}