package com.hangrycoder.jetpackcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

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
}