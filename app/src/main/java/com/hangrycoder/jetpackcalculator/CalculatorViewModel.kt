package com.hangrycoder.jetpackcalculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hangrycoder.jetpackcalculator.data.datasource.CalculatorDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CalculatorViewModel() : ViewModel() {
    val userIntent = Channel<CalculatorIntent>(Channel.UNLIMITED)

    private val _buttonsState = MutableStateFlow<ButtonState>(ButtonState.Idle)
    val buttonsState: StateFlow<ButtonState> by lazy {  _buttonsState }

    private val _calculatedValue = MutableStateFlow("")
    val calculatedValue: StateFlow<String>
        get() = _calculatedValue

    init {
        handleUserIntent()
    }

    private fun handleUserIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is CalculatorIntent.GetButtons -> {
                        _buttonsState.value =
                            ButtonState.Buttons(CalculatorDataSource.getButtons())
                    }

                    is CalculatorIntent.ClickButton -> {
                        val calculatorButton = it.calculatorButton
                        _calculatedValue.value += calculatorButton.title
                    }

                    else -> {}
                }
            }
        }
    }

}
