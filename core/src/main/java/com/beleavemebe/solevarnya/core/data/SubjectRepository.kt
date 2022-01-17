package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Subject

class SubjectRepository(private val dataSource: SubjectDataSource) {
    fun fetchAll(): List<Subject> =
        dataSource.fetchAll()

    fun fetchRandom(): Subject =
        dataSource.fetchRandom()
}
