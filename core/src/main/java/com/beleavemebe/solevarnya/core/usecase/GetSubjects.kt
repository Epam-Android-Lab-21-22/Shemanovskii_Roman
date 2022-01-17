package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.SubjectRepository
import com.beleavemebe.solevarnya.core.domain.Subject

class GetSubjects(private val subjectRepository: SubjectRepository) {
    operator fun invoke(): List<Subject> =
        subjectRepository.fetchAll()
}
