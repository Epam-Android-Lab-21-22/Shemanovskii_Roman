package com.beleavemebe.solevarnya.domain.model

import com.beleavemebe.solevarnya.domain.util.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Note(
    val text: String,
    @Serializable(DateSerializer::class) val date: Date
)
