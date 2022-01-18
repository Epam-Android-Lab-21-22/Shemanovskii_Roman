package com.beleavemebe.solevarnya.presentation.fragments

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus

abstract class CoroutinePresenter : BaseListPresenter {
    protected val presenterScope = MainScope() + CoroutineName("PresenterScope")

    @CallSuper
    override fun onDestroy() {
        presenterScope.cancel()
    }
}
