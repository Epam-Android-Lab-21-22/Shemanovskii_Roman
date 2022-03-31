package com.github.beleavemebe.bmi_calculator

sealed class HeightUnit(
    private val toMetersFactor: Double
) {
    object Centimeter : HeightUnit(0.01)
    object Feet : HeightUnit(0.3048)

    fun toMeters(x: Double): Double =
        x * toMetersFactor
}

sealed class WeightUnit(
    private val toKilosFactor: Double
) {
    object Kilogram : WeightUnit(1.0)
    object Pound : WeightUnit(0.453592)

    fun toKilos(x: Double): Double =
        x * toKilosFactor
}
