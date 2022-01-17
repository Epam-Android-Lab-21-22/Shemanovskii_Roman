package com.beleavemebe.solevarnya.presentation.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.LessonType

enum class LessonTypeEnum(
    @StringRes val stringRes: Int
) {
    SEMINAR(R.string.seminar),
    LECTURE(R.string.lecture),
    ;

    companion object {
        fun from(lessonType: LessonType): LessonTypeEnum {
            return when (lessonType) {
                LessonType.SEMINAR -> SEMINAR
                LessonType.LECTURE -> LECTURE
            }
        }
    }
}
