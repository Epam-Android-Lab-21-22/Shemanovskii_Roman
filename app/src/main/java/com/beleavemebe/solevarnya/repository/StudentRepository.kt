package com.beleavemebe.solevarnya.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Student
import com.beleavemebe.solevarnya.model.enums.Degree
import com.beleavemebe.solevarnya.util.getFaker
import com.beleavemebe.solevarnya.util.swap

object StudentRepository : ItemRepository<Student> {
    private lateinit var hardcodedStudents: MutableList<Student>

    override fun fetchAll(): List<Student> =
        hardcodedStudents

    fun init(context: Context) {
        val faker = getFaker()

        hardcodedStudents = generateSequence {
            val nameSurname = faker.name()

            val name = nameSurname.firstName()
            val surname = nameSurname.lastName()
            val degree = Degree.values().random()

            val groupYear = faker.number().numberBetween(17, 21)
            val groupProgram = context.resources.getStringArray(R.array.array_programs).random()
            val groupNumber = faker.number().numberBetween(1, 6)
            val group = "$groupYear$groupProgram$groupNumber"

            Student(name, surname, degree, group)
        }.take(16).toMutableList()
    }

    fun removeItem(student: Student) {
        hardcodedStudents -= student
    }

    fun swap(from: Int, to: Int) {
        hardcodedStudents.swap(from, to)
    }
}
