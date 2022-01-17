package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.TeacherRepository
import com.beleavemebe.solevarnya.core.domain.Teacher

class CreateTeacher(private val teacherRepository: TeacherRepository) {
    operator fun invoke(): Teacher =
        teacherRepository.createTeacher()
}
