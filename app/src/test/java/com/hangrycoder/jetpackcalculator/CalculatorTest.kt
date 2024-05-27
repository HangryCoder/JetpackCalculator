package com.hangrycoder.jetpackcalculator

import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class CalculatorTest {

    @Test
    fun testSum() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Addition.value)
        Assert.assertEquals(3.0, result, 2.0)
    }

    @Test
    fun testSubtract() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Subtract.value)
        Assert.assertEquals(-1.0, result, 1.0)
    }

    @Test
    fun testMultiply() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Multiply.value)
        Assert.assertEquals(2.0, result, 1.0)
    }

    @Test
    fun testDivide() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 2.0, Operation.Divide.value)
        Assert.assertEquals(5.0, result, 1.0)
    }

    @Test(expected = ArithmeticException::class)
    fun testDivideThrowsExceptionWhenDivisionByZero() {
        val calculator = Calculator()
        calculator.calculate(1.0, 0.0, Operation.Divide.value)
    }

    @Test
    fun testPercentage() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 2.0, Operation.Percentage.value)
        Assert.assertEquals(0.2, result, 1.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSumThrowsExceptionWhenInvalidOperatorIsPassed() {
        val calculator = Calculator()
        calculator.calculate(1.0, 2.0, Operation.Equal.value)
    }
}