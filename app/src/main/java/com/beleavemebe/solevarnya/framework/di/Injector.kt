package com.beleavemebe.solevarnya.framework.di

import com.beleavemebe.solevarnya.core.data.*
import com.beleavemebe.solevarnya.core.usecase.*

object Injector {
    lateinit var getLessons: GetLessons

    lateinit var getTeachers: GetTeachers
    lateinit var getRandomTeacher: GetRandomTeacher
    lateinit var addTeacher: AddTeacher
    lateinit var createTeacher: CreateTeacher

    lateinit var getSubjects: GetSubjects
    lateinit var getRandomSubject: GetRandomSubject

    lateinit var getStudents: GetStudents
    lateinit var removeStudent: RemoveStudent
    lateinit var swapStudents: SwapStudents

    fun init(
        lessonDataSource: LessonDataSource,
        teacherDataSource: TeacherDataSource,
        subjectDataSource: SubjectDataSource,
        studentDataSource: StudentDataSource,
    ) {
        val lessonRepository = LessonRepository(lessonDataSource)
        getLessons = GetLessons(lessonRepository)

        val teacherRepository = TeacherRepository(teacherDataSource)
        getTeachers = GetTeachers(teacherRepository)
        getRandomTeacher = GetRandomTeacher(teacherRepository)
        addTeacher = AddTeacher(teacherRepository)
        createTeacher = CreateTeacher(teacherRepository)

        val subjectRepository = SubjectRepository(subjectDataSource)
        getSubjects = GetSubjects(subjectRepository)
        getRandomSubject = GetRandomSubject(subjectRepository)

        val studentRepository = StudentRepository(studentDataSource)
        getStudents = GetStudents(studentRepository)
        removeStudent = RemoveStudent(studentRepository)
        swapStudents = SwapStudents(studentRepository)
    }
}
