package com.beleavemebe.solevarnya.repository

import com.beleavemebe.solevarnya.model.TimetableEntry
import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.model.enums.LessonType

object TimetableRepository : ItemRepository<TimetableEntry> {
    private val hardcodedTimetable = listOf(
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.MONDAY,
            hour = 13, minute = 0
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.MONDAY,
            hour = 14, minute = 40
        ),

        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.LECTURE,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 8, minute = 0
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.LECTURE,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 9, minute = 30
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.LECTURE,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.TUESDAY,
            hour = 11, minute = 10
        ),

        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 8, minute = 0
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 9, minute = 30
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.WEDNESDAY,
            hour = 11, minute = 10
        ),

        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 11, minute = 10
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 13, minute = 0
        ),
        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.LECTURE,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.THURSDAY,
            hour = 18, minute = 10
        ),


        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.SEMINAR,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.FRIDAY,
            hour = 9, minute = 30
        ),

        TimetableEntry(
            subject = SubjectRepository.fetchRandom(),
            lessonType = LessonType.LECTURE,
            teacher = TeacherRepository.fetchRandom(),
            dayOfWeek = DayOfWeek.SATURDAY,
            hour = 11, minute = 10
        ),
    )

    override fun fetchAll(): List<TimetableEntry> =
        hardcodedTimetable
}