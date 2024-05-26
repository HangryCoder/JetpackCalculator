package com.hangrycoder.jetpackcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    val buttonsList = listOf(
        CalculatorButton(id = 0, title = "AC", buttonType = ButtonType.Operation),
        CalculatorButton(id = 1, title = "C", buttonType = ButtonType.Operation),
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
                    if (currentValue == ERROR) {
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

                if (calculation.value?.isEmpty() == true || calculation.value == ERROR) {
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
                if (clearDisplay || calculation.value == ERROR) {
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
                } else if (currentValueSize > 0 && currentValue?.get(currentValueSize - 1) == '.' && calculatorButton.title == ".") {
                    return
                } else {
                    _calculation.value = currentValue + calculatorButton.title
                }
            }

            ButtonType.Calculation -> {
                if (calculation.value?.isEmpty() == true || calculation.value == ERROR) return

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
                } catch (e: ArithmeticException) {
                    _calculation.value = ERROR
                    clearDisplay = true
                    operators.clear()
                    return
                } catch (e: IllegalArgumentException) {
                    _calculation.value = ERROR
                    clearDisplay = true
                    operators.clear()
                    return
                }
                /*when (operatorId) {
                    Operation.Percentage.value -> {
                        result = calculator.percentage(result, secondNumber)
                    }

                    Operation.Divide.value -> {
                        if (secondNumber == 0.0) {
                            _calculation.value = ERROR
                            clearDisplay = true
                            operators.clear()
                            return
                        } else {
                            result = calculator.divide(result, secondNumber)
                        }
                    }

                    Operation.Multiply.value -> {
                        result = calculator.multiply(result, secondNumber)
                    }

                    Operation.Subtract.value -> {
                        result = calculator.subtract(result, secondNumber)
                    }

                    Operation.Addition.value -> {
                        result = calculator.add(result, secondNumber)
                    }

                    else -> {
                        //Throw an error
                        // _calculation.value = "Error"
                    }
                }*/
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

    private fun Double.roundOffDecimal(): Double {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING
        return df.format(this).toDouble()
    }

    companion object {
        const val ERROR = "Error"
    }
}
