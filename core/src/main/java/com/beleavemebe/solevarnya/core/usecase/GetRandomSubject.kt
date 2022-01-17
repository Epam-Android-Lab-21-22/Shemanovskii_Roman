package com.beleavemebe.solevarnya.core.usecase

import com.beleavemebe.solevarnya.core.data.SubjectRepository
import com.beleavemebe.solevarnya.core.domain.Subject

class GetRandomSubject(private val subjectRepository: SubjectRepository) {
    operator fun invoke(): Subject =
        subjectRepository.fetchRandom()
}
