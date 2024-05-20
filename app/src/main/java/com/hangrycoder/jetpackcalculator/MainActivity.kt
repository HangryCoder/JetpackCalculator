package com.hangrycoder.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Display()
        Buttons()
    }
}

@Composable
fun Display() {
    /* Box(
         modifier = Modifier
             .padding(8.dp)
             .height(80.dp)
             .fillMaxWidth()
             .background(color = MaterialTheme.colorScheme.surface)

     ) {*/
    Text(
        text = "Rs. 1500",
        textAlign = TextAlign.End,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier =
        Modifier
            .padding(8.dp)
            .height(80.dp)
            .fillMaxWidth()
           // .align(Alignment.Bottom)
            .background(color = MaterialTheme.colorScheme.surface)
    )
    //}
}

@Composable
fun Buttons() {
    operationButtonsRow()
}

@Composable
fun operationButtonsRow() {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, modifier = Modifier.weight(1f)) {
            Text(text = "A/C",  modifier = Modifier.weight(1f).clickable {  })
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
fun operationButton(onClick: () -> Unit, modifier: Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        // Text(text = "+", modifier = Modifier.background(MaterialTheme.colorScheme.))
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