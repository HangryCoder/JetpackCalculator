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

enum class Operation(val value: Int) {
    Clear(0),
    PositiveOrNegative(1),
    Percentage(2),
    Divide(3),
    Multiply(7),
    Subtract(11),
    Addition(15),
    Decimal(17),
    Equal(18)
}