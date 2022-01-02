package com.beleavemebe.solevarnya.model

import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.model.enums.LessonType
import com.beleavemebe.solevarnya.repository.SubjectRepository
import com.beleavemebe.solevarnya.repository.TeacherRepository

data class Lesson(
    val subject: Subject = SubjectRepository.fetchRandom(),
    val teacher: Teacher = TeacherRepository.fetchRandom(),
    val lessonType: LessonType,
    val dayOfWeek: DayOfWeek,
    val hour: Int,
    val minute: Int,
) {
    fun calcEndTime(): Pair<Int, Int> {
        val minsTotal = hour * 60 + minute + LESSON_DURATION_MINS
        return minsTotal / 60 to minsTotal % 60
    }

    companion object {
        private const val LESSON_DURATION_MINS = 80
    }
}
