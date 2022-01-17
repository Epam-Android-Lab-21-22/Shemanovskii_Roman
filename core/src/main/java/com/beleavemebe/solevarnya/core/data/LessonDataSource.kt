package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Lesson

interface LessonDataSource {
    fun fetchAll(): List<Lesson>
}
