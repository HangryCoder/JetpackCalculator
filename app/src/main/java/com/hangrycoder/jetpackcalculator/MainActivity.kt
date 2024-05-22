package com.hangrycoder.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.hangrycoder.jetpackcalculator.ui.theme.JetpackCalculatorTheme

class MainActivity : ComponentActivity() {

    //private val viewModel by viewModels<CalculatorViewModel>()

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

@Composable
fun Calculator(viewModel: CalculatorViewModel = viewModel()) {

    var calculatedValue by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.3f)
        ) {
            Display(calculatedValue)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .padding(0.dp, 16.dp, 0.dp, 16.dp)
        ) {
            Buttons(buttonsList = viewModel.buttonsList, onClick = {
                calculatedValue += it
            })
        }
    }
}

@Composable
fun Display(value: String) {
    val fontFamily = FontFamily(Font(R.font.digital_regular, FontWeight.Normal))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = value,
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
fun Buttons(buttonsList: List<CalculatorButton>, onClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buttonsList.dropLast(1), key = { it.id }) {
            Button(
                buttonDetail = it,
                onClick = {
                    onClick(it.title)
                }
            )
        }

        item(span = { GridItemSpan(2) }) {
            val buttonDetail = buttonsList.last()
            Button(
                buttonDetail = buttonDetail,
                onClick = {
                    //Calculate operation
                }
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
            //.fillMaxHeight()
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