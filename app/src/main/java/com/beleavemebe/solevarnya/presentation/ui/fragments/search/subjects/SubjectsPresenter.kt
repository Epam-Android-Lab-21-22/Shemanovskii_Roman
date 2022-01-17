package com.beleavemebe.solevarnya.presentation.ui.fragments.search.subjects

import com.beleavemebe.solevarnya.presentation.di.Injector

class SubjectsPresenter(
    private var view: SubjectsContract.View?,
) : SubjectsContract.Presenter {
    override fun onRecyclerReady() {
        val subjects = Injector.getSubjects()
        view?.setContent(subjects)
    }

    override fun onDestroy() {
        view = null
    }
}
