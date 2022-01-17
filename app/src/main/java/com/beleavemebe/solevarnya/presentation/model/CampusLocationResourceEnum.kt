package com.beleavemebe.solevarnya.presentation.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.CampusLocation

enum class CampusLocationResourceEnum(
    @StringRes val stringResId: Int
) {
    MOSCOW(R.string.location_moscow),
    SAINT_PETERSBURG(R.string.location_saint_petersburg),
    NIZHNY_NOVGOROD(R.string.location_nizhny_novgorod),
    PERM(R.string.location_perm),
    ;

    companion object {
        fun from(campusLocation: CampusLocation): CampusLocationResourceEnum =
            when (campusLocation) {
                CampusLocation.MOSCOW -> MOSCOW
                CampusLocation.SAINT_PETERSBURG -> SAINT_PETERSBURG
                CampusLocation.NIZHNY_NOVGOROD -> NIZHNY_NOVGOROD
                CampusLocation.PERM -> PERM
            }
    }
}
