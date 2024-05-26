package com.hangrycoder.jetpackcalculator

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import java.math.RoundingMode
import java.text.DecimalFormat

enum class ButtonState { Pressed, Idle }

fun Modifier.pressClickEffect() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val tx by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0f else -10f, label = "")
    val ty by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0f else -10f, label = "")

    this
        .graphicsLayer {
            translationX = tx
            translationY = ty
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

fun Double.roundOffDecimal(): Double {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}