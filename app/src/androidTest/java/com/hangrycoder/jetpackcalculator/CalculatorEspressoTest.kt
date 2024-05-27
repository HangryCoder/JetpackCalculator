package com.hangrycoder.jetpackcalculator

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hangrycoder.jetpackcalculator.ui.view.CalculatorView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorEspressoTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun enterFormula_showFormula() {
        rule.setContent { CalculatorView() }
    }
}