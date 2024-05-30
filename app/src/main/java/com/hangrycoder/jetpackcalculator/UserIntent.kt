package com.hangrycoder.jetpackcalculator

sealed class UserIntent {
    object GetButtons : UserIntent()
    data class ClickButton(val calculatorButton: CalculatorButton) : UserIntent()
}
