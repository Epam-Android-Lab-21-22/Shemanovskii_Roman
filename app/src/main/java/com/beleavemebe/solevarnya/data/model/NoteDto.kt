package com.beleavemebe.solevarnya.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "date")
    val date: Long,
)
