package com.beleavemebe.solevarnya.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Student
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.model.enums.AcademicRank
import com.beleavemebe.solevarnya.util.getFaker
import com.github.javafaker.Faker

object TeacherRepository : ItemRepository<Teacher> {
    private lateinit var hardcodedTeachers: MutableList<Teacher>

    override fun fetchAll(): List<Teacher> =
        hardcodedTeachers

    fun init(context: Context) {
        val faker = getFaker()

        hardcodedTeachers = generateSequence {
            createTeacher(faker, context)
        }.take(7).toMutableList()
    }

    fun createTeacher(context: Context): Teacher =
        createTeacher(getFaker(), context)

    fun addItem(newItem: Teacher) {
        hardcodedTeachers += newItem
    }

    fun fetchRandom(): Teacher =
        hardcodedTeachers.random()

    private fun createTeacher(faker: Faker, context: Context): Teacher {
        val nameSurname = faker.name()

        val name = nameSurname.firstName()
        val surname = nameSurname.lastName()
        val rank = AcademicRank.values().random()
        val location = context.resources.getStringArray(R.array.array_cities).random()

        return Teacher(name, surname, rank, location)
    }
}