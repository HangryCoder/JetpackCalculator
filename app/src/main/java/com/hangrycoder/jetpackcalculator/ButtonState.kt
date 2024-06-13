package com.hangrycoder.jetpackcalculator

import com.hangrycoder.jetpackcalculator.ui.model.CalculatorButton

sealed class ButtonState {
    object Idle : ButtonState()
    data class Buttons(val buttons: List<CalculatorButton>) : ButtonState()
}
