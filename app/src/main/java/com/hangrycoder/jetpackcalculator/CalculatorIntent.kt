package com.hangrycoder.jetpackcalculator

import com.hangrycoder.jetpackcalculator.ui.model.CalculatorButton

sealed class CalculatorIntent {
    object GetButtons : CalculatorIntent()
    data class ClickButton(val calculatorButton: CalculatorButton) : CalculatorIntent()
}
