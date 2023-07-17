package com.example.imc.ui.imc_screen

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ImcScreenViewModel : ViewModel() {
    var bmi = mutableDoubleStateOf(0.0)
        private set
    var message = mutableStateOf("")
        private set
    var selectedMode = mutableStateOf(Mode.Metric)
        private set
    var heightState = mutableStateOf(ValueState("Height", "m"))
        private set
    var weightState = mutableStateOf(ValueState("Weight", "kg"))
        private set

    fun updateHeight(it: String) {
        heightState.value = heightState.value.copy(value = it, error = null)
    }

    fun updateWeight(it: String) {
        weightState.value = weightState.value.copy(value = it, error = null)
    }

    fun calculate() {
        val height = heightState.value.toNumber()
        val weight = weightState.value.toNumber()
        if (height == null)
            heightState.value = heightState.value.copy(error = "Invalid number")
        else if (weight == null)
            weightState.value = weightState.value.copy(error = "Invalid number")
        else calculateBMI(height, weight, selectedMode.value == Mode.Metric)
    }

    private fun calculateBMI(height: Double, weight: Double, isMetric: Boolean = true) {
        bmi.value = if (isMetric)
            weight / (height * height)
        else (703 * weight) / (height * height)

        message.value = when {
            bmi.value < 18.5 -> "Underweight"
            bmi.value in 18.5..24.9 -> "Normal"
            bmi.value in 25.0..29.9 -> "Overweight"
            bmi.value in 30.0..34.9 -> "Obesity Class I"
            bmi.value in 35.0..39.9 -> "Obesity Class II"
            bmi.value >= 40.0 -> "Obesity Class III"
            else -> error("Invalid params")
        }
    }

    fun updateMode(it: Mode) {
        selectedMode.value = it
        when (selectedMode.value) {
            Mode.Imperial -> {
                heightState.value = heightState.value.copy(suffix = "inch")
                weightState.value = weightState.value.copy(suffix = "pound")
            }
            Mode.Metric -> {
                heightState.value = heightState.value.copy(suffix = "m")
                weightState.value = weightState.value.copy(suffix = "kg")
            }
        }
    }

    fun clear() {
        heightState.value = heightState.value.copy(value = "", error = null)
        weightState.value = weightState.value.copy(value = "", error = null)
        bmi.value = 0.0
        message.value = ""
    }

    enum class Mode { Imperial, Metric }
}