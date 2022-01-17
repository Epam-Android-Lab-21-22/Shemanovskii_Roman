package com.beleavemebe.solevarnya.core.domain

import com.beleavemebe.solevarnya.core.domain.enums.Degree

data class Student(
    val name: String,
    val surname: String,
    val degree: Degree,
    val group: String,
    val quote: String,
    val avatarUrl: String,
)
