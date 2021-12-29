package com.beleavemebe.solevarnya.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.model.enums.AcademicRank
import com.beleavemebe.solevarnya.util.getFaker

object TeacherRepository : ItemRepository<Teacher> {
    private lateinit var hardcodedTeachers: MutableList<Teacher>

    override fun fetchAll(): List<Teacher> =
        hardcodedTeachers

    fun init(context: Context) {
        val faker = getFaker()

        hardcodedTeachers = generateSequence {
            val nameSurname = faker.name()

            val name = nameSurname.firstName()
            val surname = nameSurname.lastName()
            val rank = AcademicRank.values().random()
            val location = context.resources.getStringArray(R.array.array_cities).random()

            Teacher(name, surname, rank, location)
        }.take(15).toMutableList()
    }
}