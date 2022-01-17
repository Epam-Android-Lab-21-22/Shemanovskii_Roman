package com.beleavemebe.solevarnya.presentation.ui.fragments

interface BaseView<T, P : BasePresenter> {
    val presenter: P

    fun setContent(content: List<T>)
}
