package com.beleavemebe.solevarnya.repository

import com.beleavemebe.solevarnya.model.Lesson
import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.model.enums.LessonType

object LessonRepository : ItemRepository<Lesson> {
    private val hardcodedLessons = listOf(
        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.MONDAY,
            hour = 13, minute = 0),
        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.MONDAY,
            hour = 14, minute = 40),

        Lesson(
            lessonType = LessonType.LECTURE,
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 8, minute = 0),
        Lesson(
            lessonType = LessonType.LECTURE,
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 9, minute = 30),
        Lesson(
            lessonType = LessonType.LECTURE,
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 11, minute = 10),

        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 8, minute = 0),
        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 9, minute = 30),
        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 11, minute = 10),

        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 11, minute = 10),
        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 13, minute = 0),
        Lesson(
            lessonType = LessonType.LECTURE,
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 18, minute = 10),


        Lesson(
            lessonType = LessonType.SEMINAR,
            dayOfWeek = DayOfWeek.FRIDAY,
            hour = 9, minute = 30),

        Lesson(
            lessonType = LessonType.LECTURE,
            dayOfWeek = DayOfWeek.SATURDAY,
            hour = 11, minute = 10),
    )

    override fun fetchAll(): List<Lesson> =
        hardcodedLessons
}