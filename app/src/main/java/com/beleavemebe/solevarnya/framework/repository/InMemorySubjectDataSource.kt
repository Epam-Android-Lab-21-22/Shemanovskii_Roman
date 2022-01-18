package com.beleavemebe.solevarnya.framework.repository

import android.content.Context
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.data.SubjectDataSource
import com.beleavemebe.solevarnya.core.domain.Subject
import com.beleavemebe.solevarnya.framework.util.getFaker

object InMemorySubjectDataSource : SubjectDataSource {
    private lateinit var hardcodedSubjects: List<Subject>

    override fun fetchAll(): List<Subject> =
        hardcodedSubjects

    override fun fetchRandom(): Subject =
        hardcodedSubjects.random()

    fun init(context: Context) {
        val faker = getFaker()
        val randomEnrolledNumber = fun(): Int = faker.number().numberBetween(0, 1000)

        hardcodedSubjects = mutableListOf(
            Subject(context.getString(R.string.english), 0, randomEnrolledNumber(), "https://www.hse.ru/mirror/pubs/share/direct/367811816"),
            Subject(context.getString(R.string.discrete_mathematics), 4, randomEnrolledNumber(), "https://topspiski.com/wp-content/uploads/2019/12/megamozg.jpg"),
            Subject(context.getString(R.string.programming), 5, randomEnrolledNumber(), "https://nnov.hse.ru/mirror/pubs/share/direct/222460939.jpg"),
            Subject(context.getString(R.string.economics), 4, randomEnrolledNumber(), "https://www.hse.ru/mirror/pubs/share/direct/425743085.jpg"),
            Subject(context.getString(R.string.calculus), 4, randomEnrolledNumber(), "https://www.hse.ru/mirror/pubs/share/direct/417937992.jpg"),
            Subject(context.getString(R.string.linear_algebra), 4, randomEnrolledNumber(), "https://nnov.hse.ru/mirror/pubs/share/direct/506094994.jpg"),
            Subject(context.getString(R.string.computer_system_architecture), 3, randomEnrolledNumber(), "https://www.hse.ru/mirror/pubs/share/direct/530869573.jpg"),
            Subject(context.getString(R.string.algorithms_and_data_structures), 6, randomEnrolledNumber(), "https://www.hse.ru/mirror/pubs/share/direct/222506035.jpg"),
            Subject(context.getString(R.string.vcs), 4, randomEnrolledNumber(), "https://www.redmineup.com/cms/assets/thumbnail/74715/720/Agile-maps-concept.png"),
            Subject(context.getString(R.string.physical_training), 0, randomEnrolledNumber(), "https://avatars.mds.yandex.net/get-zen_doc/3769340/pub_5f2037ff19fb7c1718523bd1_5f20385e6b597f2c723d51cc/scale_1200"),
        )
    }
}
