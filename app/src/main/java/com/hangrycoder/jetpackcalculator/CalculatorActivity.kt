package com.hangrycoder.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CalculatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }

    @Composable
    fun Calculator(viewModel: CalculatorViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
        val userIntent = viewModel.userIntent
        val buttonState = viewModel.buttonsState.collectAsState()
        val calculatedValue = viewModel.calculatedValue.collectAsState()
        val scope = rememberCoroutineScope()

        when (buttonState.value) {
            is ButtonState.Idle -> {
                scope.launch {
                    userIntent.send(UserIntent.GetButtons)
                }
            }

            is ButtonState.Buttons -> {
                val buttons = (buttonState.value as ButtonState.Buttons).buttons

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Display(calculatedValue.value)
                    Buttons(buttons, onClick = {
                        scope.launch {
                            userIntent.send(UserIntent.ClickButton(it))
                        }
                    })
                }
            }
        }


    }

    @Composable
    fun Display(value: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.DarkGray)
        ) {
            Text(
                text = value,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        }
    }

    @Composable
    fun Buttons(buttons: List<CalculatorButton>, onClick: (CalculatorButton) -> Unit) {
        LazyVerticalGrid(columns = GridCells.Fixed(4), content = {
            items(items = buttons, key = { it.id }) {
                Button(title = it.title) {
                    onClick(it)
                }
            }
        }, modifier = Modifier.padding(0.dp, 16.dp))
    }

    @Composable
    fun Button(title: String, onClick: () -> Unit) {
        androidx.compose.material3.Button(onClick = onClick) {
            Text(text = title, fontSize = 16.sp)
        }
    }

}