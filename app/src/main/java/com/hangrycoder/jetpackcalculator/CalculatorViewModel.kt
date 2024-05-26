package com.hangrycoder.jetpackcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    val buttonsList = CalculatorDataSource.getButtons()

    private var _calculation = MutableLiveData("")
    val calculation: LiveData<String> by lazy { _calculation }

    private val operators = arrayListOf<Int>()
    private var clearDisplay = false

    fun calculateOperation(calculatorButton: CalculatorButton) {
        when (calculatorButton.buttonType) {
            ButtonType.Operation -> {
                if (clearDisplay) {
                    clearDisplay = false
                }

                if (calculatorButton.id == Operation.AllClear.value) {
                    _calculation.value = ""
                    return
                }

                if (calculatorButton.id == Operation.Clear.value) {
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

            ButtonType.Number -> {
                if (clearDisplay || calculation.value == Constants.ERROR) {
                    clearDisplay = false
                    _calculation.value = ""
                }

                val currentValue = calculation.value
                val currentValueSize = currentValue?.length ?: 0

                //Handling prefix 0 issue
                if (currentValueSize == 1 && currentValue?.get(0) == '0' && calculatorButton.value > 0.0) {
                    _calculation.value = calculatorButton.title
                } else if (currentValueSize > 2 && currentValue?.get(currentValueSize - 1) == '0'
                    && !currentValue.get(currentValueSize - 2).isDigit()
                    && currentValue.get(currentValueSize - 2) != '.'
                    && calculatorButton.value > 0.0
                ) {
                    _calculation.value = currentValue.dropLast(1) + calculatorButton.title
                }
                //Handling prefix multiple ... issue
                else if (currentValueSize > 0 && checkIfLastValueContainsADot(
                        currentValue,
                        currentValueSize
                    ) && calculatorButton.title == "."
                ) {
                    return
                } else {
                    _calculation.value = currentValue + calculatorButton.title
                }
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


fun checkIfLastValueContainsADot(resultString: String?, size: Int): Boolean {
    val dot = '.'
    for (i in size - 1 downTo 0) {
        println(resultString?.get(i))
        if (resultString?.get(i)?.isDigit() == false && resultString.get(i) != dot) {
            return false
        } else if (resultString?.get(i) == dot) {
            return true
        }
    }
    return false
}
