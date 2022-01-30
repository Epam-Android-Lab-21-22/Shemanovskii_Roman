package com.beleavemebe.solevarnya.view.model

sealed class NoteLoadingException : Exception() {
    object DataSourceUnavailable : NoteLoadingException()
}
