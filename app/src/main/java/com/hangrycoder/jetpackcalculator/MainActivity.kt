package com.hangrycoder.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
                // A surface container using the 'background' color from the theme
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
    CalculatorButton(id = 18, title = "=", buttonType = ButtonType.Operation),
)

@Preview(showBackground = true)
@Composable
fun Calculator() {
    Column {
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
                .padding(0.dp, 8.dp, 0.dp, 16.dp)
        ) {
            Buttons()
        }
    }
}

@Composable
fun Display() {
    Box(
        modifier = Modifier
            .border(8.dp, MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = "1500",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = TextUnit(36f, TextUnitType.Sp),
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

@Composable
fun Buttons() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(16.dp, 0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(buttonsList) {
            Button(
                buttonDetail = it,
                onClick = { },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun Button(buttonDetail: CalculatorButton, onClick: () -> Unit, modifier: Modifier) {
    val backgroundColor =
        if (buttonDetail.buttonType == ButtonType.Operation) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary

    TextButton(
        onClick = {},
        modifier = Modifier
            .height(100.dp)
            //.weight(1f)
            .background(backgroundColor)
    ) {
        Text(
            text = buttonDetail.title,
            fontSize = TextUnit(24f, TextUnitType.Sp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun operationButtonsRow() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        TextButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(text = "A/C")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "+/-")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "%")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "÷")
        }
    }
}

@Composable
fun numberButton1Row() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "7")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "8")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "9")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "x")
        }
    }
}

@Composable
fun numberButton2Row() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "4")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "5")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "6")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "-")
        }
    }
}

@Composable
fun numberButton3Row() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "1")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "2")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "3")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "+")
        }
    }
}

@Composable
fun numberButton4Row() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, modifier = Modifier.weight(0.5f)) {
            Text(text = "0")
        }

        Button(onClick = {}, modifier = Modifier.weight(0.5f)) {
            Text(text = ".")
        }

        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "=")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackCalculatorTheme {
        Greeting("Android")
    }
}

data class CalculatorButton(val id: Int, val title: String, val buttonType: ButtonType)
enum class ButtonType {
    Number,
    Operation
}