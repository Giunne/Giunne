package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun NoticeTextField(
    focusManager: FocusManager,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit,
) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.gdp)
            .clip(RoundedCornerShape(12.gdp))
            .background(GPColor.White)
            .border(
                width = 2.gdp,
                color = GPColor.BorderLightGray,
                shape = RoundedCornerShape(12.gdp)
            ),
        value = text,
        textStyle = TextStyle(
            fontSize = 12.gsp,
            color = GPColor.TextBlack
        ),
        onValueChange = {
            onTextChanged(it)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(12.gdp)
            ) {
                if (text.isEmpty()) {
                    GPText(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = placeholder,
                        textColor = GPColor.TextLightGray,
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium
                    )
                }
                innerTextField()
            }
        }
    )
}