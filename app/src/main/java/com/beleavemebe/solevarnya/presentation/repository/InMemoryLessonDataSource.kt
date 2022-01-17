package com.beleavemebe.solevarnya.presentation.repository

import com.beleavemebe.solevarnya.core.data.LessonDataSource
import com.beleavemebe.solevarnya.core.data.SubjectDataSource
import com.beleavemebe.solevarnya.core.data.TeacherDataSource
import com.beleavemebe.solevarnya.core.domain.Lesson
import com.beleavemebe.solevarnya.core.domain.enums.DayOfWeek
import com.beleavemebe.solevarnya.core.domain.enums.LessonType

object InMemoryLessonDataSource : LessonDataSource {
    private lateinit var hardcodedLessons: List<Lesson>

    override fun fetchAll(): List<Lesson> =
        hardcodedLessons

    fun init(subjectDataSource: SubjectDataSource, teacherDataSource: TeacherDataSource) {
        hardcodedLessons = listOf(
            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.MONDAY,
                hour = 13, minute = 0,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.MONDAY,
                hour = 14, minute = 40,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),

            Lesson(
                lessonType = LessonType.LECTURE,
                dayOfWeek = DayOfWeek.TUESDAY,
                hour = 8, minute = 0,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.LECTURE,
                dayOfWeek = DayOfWeek.TUESDAY,
                hour = 9, minute = 30,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.LECTURE,
                dayOfWeek = DayOfWeek.TUESDAY,
                hour = 11, minute = 10,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),

            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.WEDNESDAY,
                hour = 8, minute = 0,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.WEDNESDAY,
                hour = 9, minute = 30,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.WEDNESDAY,
                hour = 11, minute = 10,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),

            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.THURSDAY,
                hour = 11, minute = 10,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.THURSDAY,
                hour = 13, minute = 0,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
            Lesson(
                lessonType = LessonType.LECTURE,
                dayOfWeek = DayOfWeek.THURSDAY,
                hour = 18, minute = 10,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),

            Lesson(
                lessonType = LessonType.SEMINAR,
                dayOfWeek = DayOfWeek.FRIDAY,
                hour = 9, minute = 30,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),

            Lesson(
                lessonType = LessonType.LECTURE,
                dayOfWeek = DayOfWeek.SATURDAY,
                hour = 11, minute = 10,
                subject = subjectDataSource.fetchRandom(),
                teacher = teacherDataSource.fetchRandom()),
        )
    }
}
