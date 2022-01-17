package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Subject

interface SubjectDataSource {
    fun fetchAll(): List<Subject>
    fun fetchRandom(): Subject
}
