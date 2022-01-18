package com.beleavemebe.solevarnya.presentation.fragments.search.subjects

import com.beleavemebe.solevarnya.core.domain.Subject
import com.beleavemebe.solevarnya.presentation.fragments.BaseListPresenter
import com.beleavemebe.solevarnya.presentation.fragments.BaseListView

interface SubjectsContract {
    interface Presenter : BaseListPresenter
    interface View : BaseListView<Subject>
}
