package com.beleavemebe.solevarnya.model

import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.model.enums.LessonType

data class TimetableEntry(
    val subject: Subject,
    val lessonType: LessonType,
    val teacher: Teacher,
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
