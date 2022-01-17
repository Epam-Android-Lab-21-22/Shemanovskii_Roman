package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Teacher

interface TeacherDataSource {
    fun fetchAll(): List<Teacher>
    fun add(teacher: Teacher): Int
    fun fetchRandom(): Teacher
}

