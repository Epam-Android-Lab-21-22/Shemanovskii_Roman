package com.beleavemebe.solevarnya.framework.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.Degree

enum class DegreeResourceEnum(
    @StringRes val stringResId: Int
) {
    BACHELOR(R.string.bachelors),
    MASTER(R.string.masters),
    ASPIRANT(R.string.aspirants),
    ;

    companion object {
        fun from(degree: Degree): DegreeResourceEnum {
            return when (degree) {
                Degree.BACHELOR -> BACHELOR
                Degree.MASTER -> MASTER
                Degree.ASPIRANT -> ASPIRANT
            }
        }
    }
}
