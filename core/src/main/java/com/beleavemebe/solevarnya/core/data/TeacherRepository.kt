package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Teacher

class TeacherRepository(private val dataSource: TeacherDataSource) {
    fun fetchAll(): List<Teacher> =
        dataSource.fetchAll()

    fun add(teacher: Teacher) =
        dataSource.add(teacher)

    fun fetchRandom(): Teacher =
        dataSource.fetchRandom()
}
