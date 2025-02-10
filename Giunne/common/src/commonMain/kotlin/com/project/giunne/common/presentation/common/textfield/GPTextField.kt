package com.project.giunne.common.presentation.common.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun GPTextField(
    modifier: Modifier = Modifier,
    paddingHorizontal: Dp = 16.gdp,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    border: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    focusManager: FocusManager = LocalFocusManager.current,
    focusable: Boolean = true,
    shape: Shape = RoundedCornerShape(10.gdp),
    placeholder: @Composable () -> Unit = {  },
    suffix: @Composable () -> Unit = {  },
    prefix: @Composable (() -> Unit)? = null,
) {
    val isFocused = remember { mutableStateOf(false) }

    val textFieldBorderColor by animateColorAsState(
        targetValue = if (isFocused.value) GPColor.MainOrangeColor else GPColor.BorderLightGray,
        animationSpec = if (isFocused.value) {
            tween(durationMillis = 200, easing = FastOutSlowInEasing)
        } else {
            tween(durationMillis = 100, easing = LinearOutSlowInEasing)
        }
    )

    val hintTextXOffset: Dp by animateDpAsState(
        targetValue = if (isFocused.value) (2.gdp) else 0.gdp,
        animationSpec = tween(durationMillis = 200)
    )

    BasicTextField(
        modifier = modifier
            .onFocusEvent {
                isFocused.value = it.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        cursorBrush = Brush.verticalGradient(colors = listOf(GPColor.TextBlack, GPColor.TextBlack)),
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        enabled = focusable,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = GPColor.White,
                        shape = shape
                    )
                    .border(
                        border = if (isFocused.value && border) {
                            BorderStroke(
                                2.gdp,
                                textFieldBorderColor
                            )
                        } else {
                            BorderStroke(
                                0.gdp,
                                GPColor.Transparent
                            )
                        },
                        shape = shape
                    ),
                verticalAlignment = if (maxLines == 1) Alignment.CenterVertically else Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(paddingHorizontal))
                if (prefix != null){
                    prefix()
                    Spacer(modifier = Modifier.width(paddingHorizontal))
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = if (maxLines > 1) 30.gdp else 0.gdp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty())
                        Box(modifier = Modifier.offset(x = hintTextXOffset)) {
                            placeholder()
                        }
                    innerTextField()
                }
                suffix()
                Spacer(modifier = Modifier.width(paddingHorizontal))
            }
        }
    )
}