package com.beleavemebe.solevarnya.core.domain

import com.beleavemebe.solevarnya.core.domain.enums.Degree
import com.beleavemebe.solevarnya.core.domain.enums.Program

data class Student(
    val name: String,
    val surname: String,
    val degree: Degree,
    val admissionYear: Int,
    val program: Program,
    val groupNumber: Int,
    val quote: String,
    val avatarUrl: String,
)
