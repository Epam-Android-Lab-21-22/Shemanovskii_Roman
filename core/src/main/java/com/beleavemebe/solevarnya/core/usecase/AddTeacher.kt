package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.TeacherRepository
import com.beleavemebe.solevarnya.core.domain.Teacher

class AddTeacher(private val teacherRepository: TeacherRepository) {
    operator fun invoke(teacher: Teacher): Int =
        teacherRepository.add(teacher)
}
