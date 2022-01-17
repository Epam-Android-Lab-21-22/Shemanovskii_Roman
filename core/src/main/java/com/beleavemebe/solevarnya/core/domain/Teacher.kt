package com.beleavemebe.solevarnya.core.domain

import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank

data class Teacher(
    val name: String,
    val surname: String,
    val rank: AcademicRank,
    val location: String,
    val avatarUrl: String,
)
