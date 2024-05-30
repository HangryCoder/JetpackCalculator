package com.hangrycoder.jetpackcalculator

sealed class ButtonState {
    object Idle : ButtonState()
    data class Buttons(val buttons: List<CalculatorButton>) : ButtonState()
}
