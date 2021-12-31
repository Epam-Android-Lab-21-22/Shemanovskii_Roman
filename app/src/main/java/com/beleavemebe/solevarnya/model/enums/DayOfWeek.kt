package com.beleavemebe.solevarnya.model.enums

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R

enum class DayOfWeek(
    @StringRes val stringRes: Int
) {
    MONDAY(R.string.monday),
    TUESDAY(R.string.tuesday),
    WEDNESDAY(R.string.wednesday),
    THURSDAY(R.string.thursday),
    FRIDAY(R.string.friday),
    SATURDAY(R.string.saturday),
    SUNDAY(R.string.sunday),
}
