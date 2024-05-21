package com.hangrycoder.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.hangrycoder.jetpackcalculator.ui.theme.JetpackCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

private val buttonsList = listOf(
    CalculatorButton(id = 0, title = "A/C", buttonType = ButtonType.Operation),
    CalculatorButton(id = 1, title = "+/-", buttonType = ButtonType.Operation),
    CalculatorButton(id = 2, title = "%", buttonType = ButtonType.Operation),
    CalculatorButton(id = 3, title = "÷", buttonType = ButtonType.Operation),

    CalculatorButton(id = 4, title = "7", buttonType = ButtonType.Number),
    CalculatorButton(id = 5, title = "8", buttonType = ButtonType.Number),
    CalculatorButton(id = 6, title = "9", buttonType = ButtonType.Number),
    CalculatorButton(id = 7, title = "x", buttonType = ButtonType.Operation),

    CalculatorButton(id = 8, title = "4", buttonType = ButtonType.Number),
    CalculatorButton(id = 9, title = "5", buttonType = ButtonType.Number),
    CalculatorButton(id = 10, title = "6", buttonType = ButtonType.Number),
    CalculatorButton(id = 11, title = "-", buttonType = ButtonType.Operation),

    CalculatorButton(id = 12, title = "1", buttonType = ButtonType.Number),
    CalculatorButton(id = 13, title = "2", buttonType = ButtonType.Number),
    CalculatorButton(id = 14, title = "3", buttonType = ButtonType.Number),
    CalculatorButton(id = 15, title = "+", buttonType = ButtonType.Operation),

    CalculatorButton(id = 16, title = "0", buttonType = ButtonType.Number),
    CalculatorButton(id = 17, title = ".", buttonType = ButtonType.Number),
    CalculatorButton(id = 18, title = "=", buttonType = ButtonType.Calculation),
)

@Preview(showBackground = true)
@Composable
fun Calculator() {
    Column(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.3f)
        ) {
            Display()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .padding(0.dp, 16.dp, 0.dp, 16.dp)
        ) {
            Buttons()
        }
    }
}

@Composable
fun Display() {
    val fontFamily = FontFamily(Font(R.font.digital_regular, FontWeight.Normal))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = "1500",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = TextUnit(60f, TextUnitType.Sp),
            fontFamily = fontFamily,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

@Composable
fun Buttons() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buttonsList.dropLast(1), key = { it.id }) {
            Button(
                buttonDetail = it,
                onClick = { }
            )
        }

        item(span = { GridItemSpan(2) }) {
            Button(
                buttonDetail = buttonsList.last(),
                onClick = { }
            )
        }
    }
}

@Composable
fun Button(buttonDetail: CalculatorButton, onClick: () -> Unit) {
    val backgroundColor = when (buttonDetail.buttonType) {
        ButtonType.Number -> MaterialTheme.colorScheme.primary
        ButtonType.Operation -> MaterialTheme.colorScheme.secondary
        ButtonType.Calculation -> MaterialTheme.colorScheme.tertiary

    }
    TextButton(
        onClick = onClick,
        modifier = Modifier
            // .fillMaxHeight()
            //.height(100.dp)
            .background(backgroundColor)
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

data class CalculatorButton(val id: Int, val title: String, val buttonType: ButtonType)
enum class ButtonType {
    Number,
    Operation,
    Calculation,
}