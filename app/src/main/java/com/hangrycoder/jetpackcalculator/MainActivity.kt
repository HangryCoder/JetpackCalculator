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
                .background(Color.Red)
        ) {

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
            text = "Rs. 1500",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

@Composable
fun Buttons() {
    Column {
        operationButtonsRow()
        numberButton1Row()
        numberButton2Row()
        numberButton3Row()
        numberButton4Row()
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


/*@Composable
fun operationButton(onClick: () -> Unit, modifier: Modifier) {
    TextButton(
        onClick = {},
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Text(text = "A/C")
    }

}*/

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