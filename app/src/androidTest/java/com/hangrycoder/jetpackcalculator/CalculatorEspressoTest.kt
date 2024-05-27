package com.hangrycoder.jetpackcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hangrycoder.jetpackcalculator.ui.theme.JetpackCalculatorTheme
import com.hangrycoder.jetpackcalculator.ui.view.CalculatorView
import com.hangrycoder.jetpackcalculator.utils.Constants
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorEspressoTest {

    @get:Rule
    val rule = createComposeRule()

    private val buttonZero = hasText("0") and (hasClickAction())
    private val buttonOne = hasText("1") and (hasClickAction())
    private val buttonTwo = hasText("2") and (hasClickAction())
    private val buttonThree = hasText("3") and (hasClickAction())
    private val buttonPlus = hasText("+") and (hasClickAction())
    private val buttonDivide = hasText("÷") and (hasClickAction())
    private val buttonEquals = hasText("=") and (hasClickAction())
    private val buttonBackspace = hasText("C") and (hasClickAction())


    @Test
    fun enterFormula_showFormula() {
        rule.setContent {
            JetpackCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorView()
                }
            }
        }

        rule.onNode(buttonOne).performClick()
        rule.onNode(buttonPlus).performClick()
        rule.onNode(buttonTwo).performClick()
        rule.onNode(buttonPlus).performClick()
        rule.onNode(buttonThree).performClick()

        rule.onNodeWithText("1+2+3").assertExists()
    }

    @Test
    fun enterInvalid_showsError() {
        rule.setContent {
            JetpackCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorView()
                }
            }
        }

        val errorString = Constants.ERROR

        rule.onNode(buttonOne).performClick()
        rule.onNode(buttonDivide).performClick()
        rule.onNode(buttonZero).performClick()
        rule.onNode(buttonZero).performClick()
        rule.onNode(buttonEquals).performClick()

        rule.onNodeWithText(errorString).assertExists()

        rule.onNode(buttonBackspace).performClick()

        rule.onNodeWithText(errorString).assertDoesNotExist()
    }
}