package com.beleavemebe.solevarnya.model

import com.beleavemebe.solevarnya.model.enums.Degree

data class Student(
    val name: String,
    val surname: String,
    val degree: Degree,
    val group: String,
)
