package com.hangrycoder.jetpackcalculator

data class CalculatorButton(
    val id: Int,
    val title: String,
    val buttonType: ButtonType,
    val value: Double = 0.0
)

enum class ButtonType {
    Number,
    Operation,
    Calculation,
}