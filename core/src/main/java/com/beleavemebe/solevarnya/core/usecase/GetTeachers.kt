package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.TeacherRepository
import com.beleavemebe.solevarnya.core.domain.Teacher

class GetTeachers(private val teacherRepository: TeacherRepository) {
    operator fun invoke(): List<Teacher> =
        teacherRepository.fetchAll()
}
