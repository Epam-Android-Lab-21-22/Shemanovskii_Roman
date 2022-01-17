package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.StudentRepository
import com.beleavemebe.solevarnya.core.domain.Student

class GetStudents(private val studentRepository: StudentRepository) {
    operator fun invoke(): List<Student> =
        studentRepository.fetchAll()
}
