package com.beleavemebe.solevarnya.model.enums

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R

enum class AcademicRank(
    @StringRes val stringResId: Int
)  {
    ASSISTANT(R.string.assistant),
    DOCENT(R.string.docent),
    PROFESSOR(R.string.professor),
}
