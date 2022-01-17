package com.beleavemebe.solevarnya.presentation.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.DayOfWeek

enum class DayOfWeekEnum(
    @StringRes val stringRes: Int
) {
    MONDAY(R.string.monday),
    TUESDAY(R.string.tuesday),
    WEDNESDAY(R.string.wednesday),
    THURSDAY(R.string.thursday),
    FRIDAY(R.string.friday),
    SATURDAY(R.string.saturday),
    SUNDAY(R.string.sunday),
    ;

    companion object {
        fun from(dayOfWeek: DayOfWeek): DayOfWeekEnum {
            return when (dayOfWeek) {
                DayOfWeek.MONDAY -> MONDAY
                DayOfWeek.TUESDAY -> TUESDAY
                DayOfWeek.WEDNESDAY -> WEDNESDAY
                DayOfWeek.THURSDAY -> THURSDAY
                DayOfWeek.FRIDAY -> FRIDAY
                DayOfWeek.SATURDAY -> SATURDAY
                DayOfWeek.SUNDAY -> SUNDAY
            }
        }
    }
}
