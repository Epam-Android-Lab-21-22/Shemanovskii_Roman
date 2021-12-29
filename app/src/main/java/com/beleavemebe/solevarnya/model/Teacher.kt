package com.beleavemebe.solevarnya.model

import com.beleavemebe.solevarnya.model.enums.AcademicRank

data class Teacher(
    val name: String,
    val surname: String,
    val rank: AcademicRank,
    val location: String
)