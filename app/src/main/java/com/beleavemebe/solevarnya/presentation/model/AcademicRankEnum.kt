package com.beleavemebe.solevarnya.presentation.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.AcademicRank

enum class AcademicRankEnum(
    @StringRes val stringResId: Int
)  {
    ASSISTANT(R.string.assistant),
    DOCENT(R.string.docent),
    PROFESSOR(R.string.professor),
    ;

    companion object {
        fun from(academicRank: AcademicRank): AcademicRankEnum {
            return when (academicRank) {
                AcademicRank.ASSISTANT -> ASSISTANT
                AcademicRank.DOCENT -> DOCENT
                AcademicRank.PROFESSOR -> PROFESSOR
            }
        }
    }
}
