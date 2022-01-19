package com.beleavemebe.solevarnya.presentation.fragments.search.subjects

import com.beleavemebe.solevarnya.core.usecase.GetSubjects

class SubjectsPresenter(
    private var view: SubjectsContract.View?,
    private val getSubjects: GetSubjects,
) : SubjectsContract.Presenter {
    override fun onRecyclerReady() {
        val subjects = getSubjects()
        view?.setContent(subjects)
    }

    override fun onDestroy() {
        view = null
    }
}
