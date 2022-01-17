package com.beleavemebe.solevarnya.core.domain

import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank
import com.beleavemebe.solevarnya.core.domain.enums.CampusLocation

data class Teacher(
    val name: String,
    val surname: String,
    val rank: AcademicRank,
    val location: CampusLocation,
    val avatarUrl: String,
)
