package com.hangrycoder.jetpackcalculator

object CalculatorButtonsDataSource {

    fun getButtons() = listOf(
        CalculatorButton(id = 0, title = "AC", type = ButtonType.Operator),
        CalculatorButton(id = 1, title = "C", type = ButtonType.Operator),
        CalculatorButton(id = 2, title = "%", type = ButtonType.Operator),
        CalculatorButton(id = 3, title = "/", type = ButtonType.Operator),

        CalculatorButton(id = 4, title = "7", type = ButtonType.Number, value = 7.0),
        CalculatorButton(id = 5, title = "8", type = ButtonType.Number, value = 8.0),
        CalculatorButton(id = 6, title = "9", type = ButtonType.Number, value = 9.0),
        CalculatorButton(id = 7, title = "*", type = ButtonType.Operator),

        CalculatorButton(id = 8, title = "4", type = ButtonType.Number, value = 4.0),
        CalculatorButton(id = 9, title = "5", type = ButtonType.Number, value = 5.0),
        CalculatorButton(id = 10, title = "6", type = ButtonType.Number, value = 6.0),
        CalculatorButton(id = 11, title = "-", type = ButtonType.Operator),

        CalculatorButton(id = 12, title = "1", type = ButtonType.Number, value = 1.0),
        CalculatorButton(id = 13, title = "2", type = ButtonType.Number, value = 2.0),
        CalculatorButton(id = 14, title = "3", type = ButtonType.Number, value = 3.0),
        CalculatorButton(id = 15, title = "+", type = ButtonType.Operator),

        CalculatorButton(id = 16, title = "=", type = ButtonType.Operator)
    )
}