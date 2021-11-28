package com.beleavemebe.solevarnya

import kotlin.math.pow

object BmiCalculator {
    fun calculateBMI(
        weightKilos: Double,
        heightMeters: Double
    ): Double {
        return weightKilos / (heightMeters.pow(2))
    }
}

object BmiDescriptor {
    fun getBmiDescription(bmi: Double): String {
        return when {
            bmi < 16 -> "Выраженный дефицит массы тела.\nНеобходима срочная консультация специалиста."
            bmi < 18.5->"Недостаточная (дефицит) масса тела.\nНеобходима консультация специалиста."
            bmi < 25 -> "Норма."
            bmi < 30 -> "Избыточная масса тела (предожирение).\nНеобходима консультация специалиста."
            bmi < 35 -> "Ожирение первой степени.\nНеобходима консультация специалиста."
            bmi < 40 -> "Ожирение второй степени.\nНеобходима консультация специалиста."
            else -> "Ожирение третьей степени (морбидное).\nНеобходима срочная консультация специалиста."
        }
    }

}
