package com.beleavemebe.solevarnya.core.data

import com.beleavemebe.solevarnya.core.domain.Student

interface StudentDataSource {
    fun fetchAll(): List<Student>
    fun remove(student: Student)
    fun swap(i: Int, j: Int)
}
