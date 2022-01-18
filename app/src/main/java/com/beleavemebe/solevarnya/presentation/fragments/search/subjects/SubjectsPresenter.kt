package com.beleavemebe.solevarnya.presentation.fragments.search.subjects

import com.beleavemebe.solevarnya.framework.di.Injector

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
