package com.beleavemebe.solevarnya.model.enums

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R

enum class LessonType(
    @StringRes val stringRes: Int
) {
    SEMINAR(R.string.seminar),
    LECTURE(R.string.lecture),
}