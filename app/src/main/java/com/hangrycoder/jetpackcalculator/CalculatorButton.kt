package com.hangrycoder.jetpackcalculator

data class CalculatorButton(
    val id: Int,
    val title: String,
    val type: ButtonType = ButtonType.Number,
    val value: Double = 0.0,
    )

enum class ButtonType {
    Number,
    Operator
}