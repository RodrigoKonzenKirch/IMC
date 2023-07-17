package com.example.imc

data class ValueState(
    val label: String, // label
    val suffix: String, // units
    val value: String = "", // A string representing the user's input value.
    val error: String? = null // error message if any
) {
    fun toNumber() = value.toDoubleOrNull()
}
