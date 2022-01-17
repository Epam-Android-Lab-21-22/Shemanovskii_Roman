package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.StudentRepository

class SwapStudents(private val studentRepository: StudentRepository) {
    operator fun invoke(i: Int, j: Int) =
        studentRepository.swap(i, j)
}
