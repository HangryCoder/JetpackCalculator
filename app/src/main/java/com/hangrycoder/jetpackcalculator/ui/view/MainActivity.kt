package com.hangrycoder.jetpackcalculator.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hangrycoder.jetpackcalculator.state.ButtonState
import com.hangrycoder.jetpackcalculator.intent.CalculatorIntent
import com.hangrycoder.jetpackcalculator.R
import com.hangrycoder.jetpackcalculator.ui.model.ButtonType
import com.hangrycoder.jetpackcalculator.ui.model.CalculatorButton
import com.hangrycoder.jetpackcalculator.ui.theme.JetpackCalculatorTheme
import com.hangrycoder.jetpackcalculator.utils.NoRippleInteractionSource
import com.hangrycoder.jetpackcalculator.utils.pressClickEffect
import com.hangrycoder.jetpackcalculator.ui.viewmodel.CalculatorViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {

    // var calculatedValue by remember { mutableStateOf("") }
    val calculatedValue = viewModel.calculation.observeAsState()
    val calculatorIntent = viewModel.calculatorIntent
    val buttonState = viewModel.buttonState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = calculatorIntent) {
        calculatorIntent.send(CalculatorIntent.GetButtons)
    }

    when (buttonState.value) {
        is ButtonState.Buttons -> {
            val buttons = (buttonState.value as ButtonState.Buttons).buttons

            Column(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Display(calculatedValue.value!!)
                }
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(0.dp, 16.dp, 0.dp, 16.dp)
                ) {
                    Buttons(buttonsList = buttons, onClick = {
                        scope.launch {
                            calculatorIntent.send(CalculatorIntent.ClickButton(it))
                        }
                    })
                }
            }

        }

      /*  is ButtonState.Display -> {
            //val text = (buttonState.value as ButtonState.Display).text
            //calculatedValue = text
        }*/

        else -> {}
    }


}

@Composable
fun CalculatorView() {

}

@Composable
fun Display(value: String) {
    val fontFamily = FontFamily(Font(R.font.digital_regular, FontWeight.Normal))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .horizontalScroll(rememberScrollState(), reverseScrolling = true)
    ) {
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = TextUnit(60f, TextUnitType.Sp),
            fontFamily = fontFamily,
            maxLines = 1,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

@Composable
fun Buttons(buttonsList: List<CalculatorButton>, onClick: (CalculatorButton) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buttonsList.dropLast(1), key = { it.id }) {
            Button(
                buttonDetail = it,
                onClick = {
                    onClick(it)
                }
            )
        }

        item(span = { GridItemSpan(2) }) {
            val buttonDetail = buttonsList.last()
            EqualButton(
                buttonDetail = buttonDetail,
                onClick = {
                    onClick(buttonDetail)
                }
            )
        }
    }
}

@Composable
fun Button(buttonDetail: CalculatorButton, onClick: () -> Unit) {
    val foregroundColor = when (buttonDetail.buttonType) {
        ButtonType.Number -> MaterialTheme.colorScheme.primary
        ButtonType.Operation -> MaterialTheme.colorScheme.secondary
        ButtonType.Calculation -> MaterialTheme.colorScheme.tertiary
    }
    val backgroundColor = when (buttonDetail.buttonType) {
        ButtonType.Number -> MaterialTheme.colorScheme.onPrimaryContainer
        ButtonType.Operation -> MaterialTheme.colorScheme.onSecondaryContainer
        ButtonType.Calculation -> MaterialTheme.colorScheme.onTertiaryContainer
    }

    androidx.compose.material3.Button(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = foregroundColor
        ),
        interactionSource = remember { NoRippleInteractionSource() },
        modifier = Modifier
            .aspectRatio(1f)
            .background(backgroundColor)
            .pressClickEffect()
    ) {
        Text(
            text = buttonDetail.title,
            fontSize = TextUnit(24f, TextUnitType.Sp),
            fontFamily = FontFamily(Font(R.font.noto_sans_regular, FontWeight.Normal)),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun EqualButton(buttonDetail: CalculatorButton, onClick: () -> Unit) {
    val foregroundColor = MaterialTheme.colorScheme.tertiary
    val backgroundColor = MaterialTheme.colorScheme.onTertiaryContainer

    androidx.compose.material3.Button(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = foregroundColor
        ),
        interactionSource = remember { NoRippleInteractionSource() },
        modifier = Modifier
            .aspectRatio(2.1f, true)
            .background(backgroundColor)
            .pressClickEffect()
    ) {
        Text(
            text = buttonDetail.title,
            fontSize = TextUnit(24f, TextUnitType.Sp),
            modifier = Modifier.padding(8.dp, 8.dp),
            fontFamily = FontFamily(Font(R.font.noto_sans_regular, FontWeight.Normal)),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}