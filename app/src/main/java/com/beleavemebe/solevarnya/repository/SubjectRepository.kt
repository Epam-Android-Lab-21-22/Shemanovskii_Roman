package com.beleavemebe.solevarnya.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.model.Subject
import com.beleavemebe.solevarnya.util.getFaker


object SubjectRepository : ItemRepository<Subject> {
    private lateinit var hardcodedSubjects: MutableList<Subject>

    override fun fetchAll(): List<Subject> =
        hardcodedSubjects

    fun init(context: Context) {
        val faker = getFaker()
        val randomEnrolledNumber = fun(): Int {
            return faker.number().numberBetween(0, 1000)
        }

        hardcodedSubjects = mutableListOf(
            Subject(context.getString(R.string.english), 0, randomEnrolledNumber()),
            Subject(context.getString(R.string.discrete_mathematics), 4, randomEnrolledNumber()),
            Subject(context.getString(R.string.programming), 5, randomEnrolledNumber()),
            Subject(context.getString(R.string.economics), 4, randomEnrolledNumber()),
            Subject(context.getString(R.string.calculus), 4, randomEnrolledNumber()),
            Subject(context.getString(R.string.linear_algebra), 4, randomEnrolledNumber()),
            Subject(context.getString(R.string.computer_system_architecture), 3, randomEnrolledNumber()),
            Subject(context.getString(R.string.algorithms_and_data_structures), 6, randomEnrolledNumber()),
            Subject(context.getString(R.string.vcs), 4, randomEnrolledNumber()),
            Subject(context.getString(R.string.physical_training), 0, randomEnrolledNumber()),
        )
    }
}
