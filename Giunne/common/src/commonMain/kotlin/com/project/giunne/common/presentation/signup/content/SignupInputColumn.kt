package com.project.giunne.common.presentation.signup.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun SignupInputColumn(
    modifier: Modifier = Modifier,
    sideContent: @Composable () -> Unit = {  },
    titleText: String,
    text: String,
    hintText: String = "",
    onTextChanged: (String) -> Unit,
    focusable: Boolean = true,
    focusManager: FocusManager,
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.gdp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GPText(
                text = titleText,
                textSize = 14.gsp,
                textColor = GPColor.TextBlack,
                fontFamily = GPFontFamily.Bold
            )
            sideContent()
        }
        SpH(6.gdp)
        GPTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.gdp)
                .background(color = GPColor.White),
            shape = RoundedCornerShape(10.gdp),
            textStyle = TextStyle(
                color = GPColor.TextBlack,
                fontSize = 14.gsp,
                fontFamily = GPFontFamily.Medium
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = if (titleText != "코드 입력") ImeAction.Next else ImeAction.Done
            ),
            visualTransformation = if (titleText == "비밀번호" || titleText == "비밀번호 확인")
                PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                onDone = { focusManager.clearFocus() }
            ),
            placeholder = {
                if (hintText.isNotEmpty()) {
                    GPText(
                        text = hintText,
                        textColor = GPColor.TextLightGray,
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Medium
                    )
                }
            },
            focusable = focusable,
            value = text,
            onValueChange = { onTextChanged(it) },
            border = true,
        )
    }
}