package com.hangrycoder.jetpackcalculator

import java.lang.IllegalArgumentException

class Calculator {

    private fun add(firstNum: Double, secondNum: Double): Double {
        return firstNum + secondNum
    }

    private fun subtract(firstNum: Double, secondNum: Double): Double {
        return firstNum - secondNum
    }

    private fun multiply(firstNum: Double, secondNum: Double): Double {
        return firstNum * secondNum
    }

    private fun divide(firstNum: Double, secondNum: Double): Double {
        if (secondNum == 0.0) {
            throw ArithmeticException("Division by zero")
        }
        return firstNum / secondNum
    }

    private fun percentage(firstNum: Double, secondNum: Double): Double {
        return (firstNum * secondNum) / 100
    }

    fun calculate(firstNum: Double, secondNum: Double, operatorId: Int): Double {
        when (operatorId) {
            Operation.Percentage.value -> {
                return percentage(firstNum, secondNum)
            }

            Operation.Divide.value -> {
                return divide(firstNum, secondNum)
            }

            Operation.Multiply.value -> {
                return multiply(firstNum, secondNum)
            }

            Operation.Subtract.value -> {
                return subtract(firstNum, secondNum)
            }

            Operation.Addition.value -> {
                return add(firstNum, secondNum)
            }

            else -> {
                throw IllegalArgumentException("Invalid Operation")
            }
        }
    }
}