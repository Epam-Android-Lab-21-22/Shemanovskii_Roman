package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.LessonRepository
import com.beleavemebe.solevarnya.core.domain.Lesson

class GetLessons(private val lessonRepository: LessonRepository) {
    operator fun invoke(): List<Lesson> =
        lessonRepository.fetchAll()
}
