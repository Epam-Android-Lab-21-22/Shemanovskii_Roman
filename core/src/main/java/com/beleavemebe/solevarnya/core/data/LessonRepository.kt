package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Lesson

class LessonRepository(private val dataSource: LessonDataSource) {
    fun fetchAll(): List<Lesson> =
        dataSource.fetchAll()
}
