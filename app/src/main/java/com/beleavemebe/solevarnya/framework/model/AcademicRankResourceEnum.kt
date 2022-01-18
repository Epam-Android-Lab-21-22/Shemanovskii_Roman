package com.beleavemebe.solevarnya.framework.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank

enum class AcademicRankResourceEnum(
    @StringRes val stringResId: Int
)  {
    ASSISTANT(R.string.assistant),
    DOCENT(R.string.docent),
    PROFESSOR(R.string.professor),
    ;

    companion object {
        fun from(academicRank: AcademicRank): AcademicRankResourceEnum {
            return when (academicRank) {
                AcademicRank.ASSISTANT -> ASSISTANT
                AcademicRank.DOCENT -> DOCENT
                AcademicRank.PROFESSOR -> PROFESSOR
            }
        }
    }
}
