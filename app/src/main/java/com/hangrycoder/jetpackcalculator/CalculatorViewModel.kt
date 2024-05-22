package com.hangrycoder.jetpackcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    val buttonsList = listOf(
        CalculatorButton(id = 0, title = "A/C", buttonType = ButtonType.Operation),
        CalculatorButton(id = 1, title = "+/-", buttonType = ButtonType.Operation),
        CalculatorButton(id = 2, title = "%", buttonType = ButtonType.Operation),
        CalculatorButton(id = 3, title = "÷", buttonType = ButtonType.Operation),

        CalculatorButton(id = 4, title = "7", buttonType = ButtonType.Number, 7.0),
        CalculatorButton(id = 5, title = "8", buttonType = ButtonType.Number, 8.0),
        CalculatorButton(id = 6, title = "9", buttonType = ButtonType.Number, 9.0),
        CalculatorButton(id = 7, title = "×", buttonType = ButtonType.Operation),

        CalculatorButton(id = 8, title = "4", buttonType = ButtonType.Number, 4.0),
        CalculatorButton(id = 9, title = "5", buttonType = ButtonType.Number, 5.0),
        CalculatorButton(id = 10, title = "6", buttonType = ButtonType.Number, 6.0),
        CalculatorButton(id = 11, title = "-", buttonType = ButtonType.Operation),

        CalculatorButton(id = 12, title = "1", buttonType = ButtonType.Number, 1.0),
        CalculatorButton(id = 13, title = "2", buttonType = ButtonType.Number, 2.0),
        CalculatorButton(id = 14, title = "3", buttonType = ButtonType.Number, 3.0),
        CalculatorButton(id = 15, title = "+", buttonType = ButtonType.Operation),

        CalculatorButton(id = 16, title = "0", buttonType = ButtonType.Number, 0.0),
        CalculatorButton(id = 17, title = ".", buttonType = ButtonType.Number),
        CalculatorButton(id = 18, title = "=", buttonType = ButtonType.Calculation),
    )

    private var _calculatedValue = MutableLiveData(0.0)
    val calculatedValue: LiveData<Double> by lazy { _calculatedValue }

    private var _calculation = MutableLiveData("")
    val calculation: LiveData<String> by lazy { _calculation }

    fun calculateOperation(calculatorButton: CalculatorButton) {
        val currentValue = calculatedValue.value ?: 0.0
        val newValue = calculatorButton.value

        when (calculatorButton.buttonType) {
            ButtonType.Operation -> {
                //Do something
                _calculation.value = calculation.value + calculatorButton.title
            }

            ButtonType.Number -> {
                _calculation.value = calculation.value + calculatorButton.title
            }

            ButtonType.Calculation -> {

            }
        }

        when (calculatorButton.id) {
            Operation.Clear.value -> {
                _calculatedValue.value = 0.0
            }

            Operation.PositiveOrNegative.value -> {
                _calculatedValue.value = -currentValue
            }

            Operation.Percentage.value -> {
                _calculatedValue.value = currentValue % newValue
            }

            Operation.Divide.value -> {
                _calculatedValue.value = currentValue / newValue
            }

            Operation.Multiply.value -> {
                _calculatedValue.value = currentValue * newValue
            }

            Operation.Subtract.value -> {
                _calculatedValue.value = currentValue - newValue
            }

            Operation.Addition.value -> {
                _calculatedValue.value = currentValue + newValue
            }

            Operation.Decimal.value -> {
                // _calculatedValue.value = currentValue * newValue.toDouble()
            }

            Operation.Equal.value -> {
                //  _calculatedValue.value = currentValue * newValue.toDouble()
            }
        }
    }
}