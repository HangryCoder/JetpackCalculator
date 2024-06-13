package com.hangrycoder.jetpackcalculator

import com.hangrycoder.jetpackcalculator.ui.model.ButtonType
import com.hangrycoder.jetpackcalculator.ui.model.CalculatorButton

object CalculatorButtonsDataSource {

    fun getButtons() = listOf(
        CalculatorButton(id = 0, title = "AC", buttonType = ButtonType.Operation),
        CalculatorButton(id = 1, title = "C", buttonType = ButtonType.Operation),
        CalculatorButton(id = 2, title = "%", buttonType = ButtonType.Operation),
        CalculatorButton(id = 3, title = "/", buttonType = ButtonType.Operation),

        CalculatorButton(id = 4, title = "7", buttonType = ButtonType.Number, value = 7.0),
        CalculatorButton(id = 5, title = "8", buttonType = ButtonType.Number, value = 8.0),
        CalculatorButton(id = 6, title = "9", buttonType = ButtonType.Number, value = 9.0),
        CalculatorButton(id = 7, title = "*", buttonType = ButtonType.Operation),

        CalculatorButton(id = 8, title = "4", buttonType = ButtonType.Number, value = 4.0),
        CalculatorButton(id = 9, title = "5", buttonType = ButtonType.Number, value = 5.0),
        CalculatorButton(id = 10, title = "6", buttonType = ButtonType.Number, value = 6.0),
        CalculatorButton(id = 11, title = "-", buttonType = ButtonType.Operation),

        CalculatorButton(id = 12, title = "1", buttonType = ButtonType.Number, value = 1.0),
        CalculatorButton(id = 13, title = "2", buttonType = ButtonType.Number, value = 2.0),
        CalculatorButton(id = 14, title = "3", buttonType = ButtonType.Number, value = 3.0),
        CalculatorButton(id = 15, title = "+", buttonType = ButtonType.Operation),

        CalculatorButton(id = 16, title = "=", buttonType = ButtonType.Operation)
    )
}