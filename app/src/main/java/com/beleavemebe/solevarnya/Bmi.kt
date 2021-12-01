package com.beleavemebe.solevarnya

import androidx.annotation.StringRes
import kotlin.math.pow

object BmiCalculator {
    fun calculateBMI(
        weightKilos: Double,
        heightMeters: Double
    ): Double? {
        return when {
            weightKilos == 0.0 || heightMeters == 0.0 -> null
            else -> weightKilos / (heightMeters.pow(2))
        }
    }
}

object BmiDescriptor {
    @StringRes
    fun getBmiDescription(bmi: Double): Int {
        return when {
            bmi < 16 -> R.string.bmi_1
            bmi < 18.5 -> R.string.bmi_2
            bmi < 25 -> R.string.bmi_3
            bmi < 30 -> R.string.bmi_4
            bmi < 35 -> R.string.bmi_5
            bmi < 40 -> R.string.bmi_6
            else -> R.string.bmi_7
        }
    }

}
