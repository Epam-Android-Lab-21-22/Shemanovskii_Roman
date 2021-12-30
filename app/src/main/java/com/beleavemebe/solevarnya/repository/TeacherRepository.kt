package com.beleavemebe.solevarnya.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.model.enums.AcademicRank
import com.beleavemebe.solevarnya.util.getFaker
import com.github.javafaker.Faker

object TeacherRepository : ItemRepository<Teacher> {
    private lateinit var hardcodedTeachers: MutableList<Teacher>

    fun init(context: Context) {
        val faker = getFaker()

        hardcodedTeachers = generateSequence {
            getRandomTeacher(faker, context)
        }.take(1).toMutableList()
    }

    private fun getRandomTeacher(faker: Faker, context: Context): Teacher {
        val nameSurname = faker.name()

        val name = nameSurname.firstName()
        val surname = nameSurname.lastName()
        val rank = AcademicRank.values().random()
        val location = context.resources.getStringArray(R.array.array_cities).random()

        return Teacher(name, surname, rank, location)
    }

    override fun fetchAll(): List<Teacher> =
        hardcodedTeachers

    fun getRandomTeacher(context: Context): Teacher =
        getRandomTeacher(getFaker(), context)

    fun addItem(newItem: Teacher) {
        hardcodedTeachers += newItem
    }
}