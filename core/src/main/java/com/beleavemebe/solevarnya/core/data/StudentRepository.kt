package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Student

class StudentRepository(private val dataSource: StudentDataSource) {
    fun remove(student: Student) =
        dataSource.remove(student)

    fun swap(i: Int, j: Int) =
        dataSource.swap(i ,j)

    fun fetchAll(): List<Student> =
        dataSource.fetchAll()
}
