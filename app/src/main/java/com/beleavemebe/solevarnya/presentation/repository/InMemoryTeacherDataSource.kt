package com.beleavemebe.solevarnya.presentation.repository

import com.beleavemebe.solevarnya.core.data.TeacherDataSource
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank
import com.beleavemebe.solevarnya.core.domain.enums.CampusLocation
import com.beleavemebe.solevarnya.presentation.util.getFaker
import com.github.javafaker.Faker

object InMemoryTeacherDataSource : TeacherDataSource {
    private lateinit var randomTeachers: MutableList<Teacher>

    override fun fetchAll(): List<Teacher> =
        randomTeachers

    override fun add(teacher: Teacher): Int {
        randomTeachers.add(teacher)
        return randomTeachers.lastIndex
    }

    override fun fetchRandom(): Teacher =
        randomTeachers.random()

    override fun createTeacher(): Teacher =
        createTeacher(getFaker())

    fun init() {
        val faker = getFaker()

        randomTeachers = generateSequence {
            createTeacher(faker)
        }
            .take(7)
            .toMutableList()
    }

    fun createTeacher(faker: Faker): Teacher {
        val nameSurname = faker.name()

        val name = nameSurname.firstName()
        val surname = nameSurname.lastName()
        val rank = AcademicRank.values().random()
        val location = CampusLocation.values().random()
        val avatarUrl = AnimeAvatars.getRandomAvatarUrl()

        return Teacher(name, surname, rank, location, avatarUrl)
    }
}
