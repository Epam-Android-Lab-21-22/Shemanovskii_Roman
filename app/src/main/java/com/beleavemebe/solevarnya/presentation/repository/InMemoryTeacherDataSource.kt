package com.beleavemebe.solevarnya.presentation.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.data.TeacherDataSource
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank
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

    fun init(context: Context) {
        val faker = getFaker()

        randomTeachers = generateSequence {
            createTeacher(faker, context)
        }
            .take(7)
            .toMutableList()
    }

    fun createTeacher(faker: Faker, context: Context): Teacher {
        val nameSurname = faker.name()

        val name = nameSurname.firstName()
        val surname = nameSurname.lastName()
        val rank = AcademicRank.values().random()
        val location = context.resources.getStringArray(R.array.array_cities).random()
        val avatarUrl = AnimeAvatars.getRandomAvatarUrl()

        return Teacher(name, surname, rank, location, avatarUrl)
    }
}
