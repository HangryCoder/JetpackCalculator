package com.hangrycoder.jetpackcalculator.data.model

enum class Operation(val value: Int) {
    AllClear(0),
    Clear(1),
    Percentage(2),
    Divide(3),
    Multiply(7),
    Subtract(11),
    Addition(15),
    Decimal(17),
    Equal(18)
}