package com.beleavemebe.solevarnya.core.domain

import com.beleavemebe.solevarnya.core.domain.enums.DayOfWeek
import com.beleavemebe.solevarnya.core.domain.enums.LessonType

data class Lesson(
    val subject: Subject,
    val teacher: Teacher,
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
