package com.hangrycoder.jetpackcalculator.ui.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hangrycoder.jetpackcalculator.data.model.ButtonType
import com.hangrycoder.jetpackcalculator.data.Calculator
import com.hangrycoder.jetpackcalculator.data.model.CalculatorButton
import com.hangrycoder.jetpackcalculator.data.datasource.CalculatorDataSource
import com.hangrycoder.jetpackcalculator.data.model.Operation
import com.hangrycoder.jetpackcalculator.utils.Constants
import com.hangrycoder.jetpackcalculator.utils.roundOffDecimal

class CalculatorViewModel : ViewModel() {

    val buttonsList = CalculatorDataSource.getButtons()

    private var _calculation = MutableLiveData("")
    val calculation: LiveData<String> by lazy { _calculation }

    private val operators = arrayListOf<Int>()
    private var clearDisplay = false

    fun calculateOperation(calculatorButton: CalculatorButton) {
        when (calculatorButton.buttonType) {
            ButtonType.Operation -> {
                insertOperator(calculatorButton)
            }

            ButtonType.Number -> {
                insertNumber(calculatorButton)
            }

            ButtonType.Calculation -> {
                if (calculation.value?.isEmpty() == true || calculation.value == Constants.ERROR) return

                println("Calculation")
                val resultArray =
                    calculation.value?.split("+", "-", "÷", "×", "%")?.filterNot { it.isEmpty() }
                if (resultArray != null) {
                    calculateResult(resultArray)
                }
            }
        }
    }

    private fun insertOperator(calculatorButton: CalculatorButton) {
        if (clearDisplay) {
            clearDisplay = false
        }

        when (calculatorButton.id) {
            Operation.AllClear.value -> {
                _calculation.value = ""
                return
            }

            Operation.Clear.value -> {
                val currentValue = calculation.value

                if (currentValue?.isEmpty() == true) {
                    return
                }
                if (currentValue == Constants.ERROR) {
                    _calculation.value = ""
                    return
                }

                val updatedValue = currentValue?.dropLast(1)
                if (updatedValue?.isEmpty() == true) {
                    operators.clear()
                }

                _calculation.value = updatedValue
                return
            }

            else -> {
                /* if (calculatorButton.id == Operation.Subtract.value && calculation.value?.isEmpty() == true) {
          _calculation.value = calculatorButton.title
          return
      }*/

                if (calculation.value?.isEmpty() == true || calculation.value == Constants.ERROR) {
                    //Do Nothing
                    //Cause remaining operators should not come before number
                    return
                }

                if (calculation.value?.last()?.isDigit() == true) {
                    _calculation.value = calculation.value + calculatorButton.title
                } else {
                    _calculation.value = calculation.value?.dropLast(1) + calculatorButton.title
                    operators.removeLastOrNull()
                }
                operators.add(calculatorButton.id)
            }
        }
    }

    private fun prefixZero(resultString: String?, calculatorButton: CalculatorButton): Boolean {
        val resultSize = resultString?.length ?: 0
        val zero = '0'
        return resultSize == 1 && resultString?.get(0) == zero && calculatorButton.title.isDigitsOnly()
    }

    private fun containsMultipleZero(
        resultString: String?,
        calculatorButton: CalculatorButton
    ): Boolean {
        val size = resultString?.length ?: 0
        val dot = '.'
        val zero = '0'
        return (size > 1 && resultString?.get(size - 1) == zero
                && !resultString[size - 2].isDigit()
                && resultString[size - 2] != dot
                && (calculatorButton.value > 0.0 || calculatorButton.title == Constants.ZERO))
    }

    private fun containsDot(resultString: String?): Boolean {
        val size = resultString?.length ?: 0
        val dot = '.'
        for (i in size - 1 downTo 0) {
            if (resultString?.get(i)?.isDigit() == false && resultString[i] != dot) {
                return false
            } else if (resultString?.get(i) == dot) {
                return true
            }
        }
        return false
    }

    private fun insertNumber(calculatorButton: CalculatorButton) {
        if (clearDisplay || calculation.value == Constants.ERROR) {
            clearDisplay = false
            _calculation.value = ""
        }

        val currentValue = calculation.value

        if (prefixZero(currentValue, calculatorButton)) {
            _calculation.value = calculatorButton.title
        } else if (containsMultipleZero(currentValue, calculatorButton)) {
            _calculation.value = currentValue?.dropLast(1) + calculatorButton.title
        } else if (containsDot(currentValue) && calculatorButton.title == Constants.DOT) {
            return
        } else {
            _calculation.value = currentValue + calculatorButton.title
        }
    }

    private fun calculateResult(numbers: List<String>) {
        var i = 0
        val firstNumber = numbers[i].toDoubleOrNull() ?: return
        var result = firstNumber
        operators.forEach { operatorId ->
            println("Operator $operatorId")
            if (i + 1 < numbers.size && numbers[i + 1].isNotEmpty()) {
                val secondNumber = numbers[i + 1].toDouble()

                println("First Number $result")
                println("Second Number $secondNumber")
                i += 1

                val calculator = Calculator()
                try {
                    result = calculator.calculate(result, secondNumber, operatorId)
                } catch (e: Exception) {
                    _calculation.value = Constants.ERROR
                    clearDisplay = true
                    operators.clear()
                    return
                }

                println("Result $result")
                println("Check if decimal ${result % 1}")
            }
        }

        val hasTrailingValues = result % 1
        val resultString = if (hasTrailingValues == 0.0) {
            result.toInt().toString()
        } else {
            result.roundOffDecimal().toString()
        }
        _calculation.value = resultString
        clearDisplay = true
        operators.clear()
    }
}
