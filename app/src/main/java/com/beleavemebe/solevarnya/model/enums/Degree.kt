package com.beleavemebe.solevarnya.model.enums

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R

enum class Degree(
    @StringRes val stringResId: Int
) {
    BACHELOR(R.string.bachelors),
    MASTER(R.string.masters),
    ASPIRANT(R.string.aspirants),
}
