package com.hangrycoder.jetpackcalculator

import com.hangrycoder.jetpackcalculator.data.Calculator
import com.hangrycoder.jetpackcalculator.data.model.Operation
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class CalculatorTest {

    @Test
    fun testSum() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Addition.value)
        Assert.assertEquals(3.0, result, 0.0)
    }

    @Test
    fun testSumFails() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Addition.value)
        Assert.assertNotEquals(-1.0, result, 0.0)
    }

    @Test
    fun testSubtract() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Subtract.value)
        Assert.assertEquals(-1.0, result, 0.0)
    }

    @Test
    fun testSubtractFails() {
        val calculator = Calculator()
        val result = calculator.calculate(5.0, 2.0, Operation.Subtract.value)
        Assert.assertNotEquals(6.0, result, 0.0)
    }

    @Test
    fun testMultiply() {
        val calculator = Calculator()
        val result = calculator.calculate(1.0, 2.0, Operation.Multiply.value)
        Assert.assertEquals(2.0, result, 0.0)
    }

    @Test
    fun testMultiplyFails() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 12.0, Operation.Multiply.value)
        Assert.assertNotEquals(100.0, result, 0.0)
    }

    @Test
    fun testDivide() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 2.0, Operation.Divide.value)
        Assert.assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testDivideFails() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 5.0, Operation.Divide.value)
        Assert.assertNotEquals(3.0, result, 0.0)
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
        Assert.assertEquals(0.2, result, 0.0)
    }

    @Test
    fun testPercentageFails() {
        val calculator = Calculator()
        val result = calculator.calculate(10.0, 2.0, Operation.Percentage.value)
        Assert.assertNotEquals(1.0, result, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSumThrowsExceptionWhenInvalidOperatorIsPassed() {
        val calculator = Calculator()
        calculator.calculate(1.0, 2.0, Operation.Equal.value)
    }
}