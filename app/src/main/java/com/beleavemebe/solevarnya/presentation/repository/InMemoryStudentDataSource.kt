package com.beleavemebe.solevarnya.presentation.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.data.StudentDataSource
import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.core.domain.enums.Degree
import com.beleavemebe.solevarnya.presentation.util.getFaker
import com.beleavemebe.solevarnya.presentation.util.swap

object InMemoryStudentDataSource : StudentDataSource {
    private lateinit var randomStudents: MutableList<Student>

    override fun fetchAll(): List<Student> {
        return randomStudents
    }

    override fun remove(student: Student) {
        randomStudents.remove(student)
    }

    override fun swap(i: Int, j: Int) {
        randomStudents.swap(i, j)
    }

    fun init(context: Context) {
        val faker = getFaker()

        randomStudents = generateSequence {
            val nameSurname = faker.name()

            val name = nameSurname.firstName()
            val surname = nameSurname.lastName()
            val degree = Degree.values().random()

            val groupYear = faker.number().numberBetween(17, 21)
            val groupProgram = context.resources.getStringArray(R.array.array_programs).random()
            val groupNumber = faker.number().numberBetween(1, 6)
            val group = "$groupYear$groupProgram$groupNumber"
            val quote = faker.twinPeaks().quote()
            val avatarUrl = PepeAvatars.getRandomAvatarUrl()

            Student(name, surname, degree, group, quote, avatarUrl)
        }
            .take(12)
            .toMutableList()
    }
}
