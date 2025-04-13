package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_arrow_up
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommentInputRow(
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    isPassed: Boolean = false,
    onSendButtonClicked: (String) -> Unit,
    onCertButtonClicked: () -> Unit = {},
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .padding(horizontal = 8.gdp, vertical = 8.gdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPTextField(
            modifier = Modifier
                .weight(1f)
                .height(32.gdp)
                .background(color = GPColor.BackgroundGray_F6F6F6),
            shape = RoundedCornerShape(12.gdp),
            textStyle = TextStyle(
                color = GPColor.TextBlack,
                fontSize = 10.gsp,
                fontFamily = GPFontFamily.Medium
            ),
            value = text,
            onValueChange = { text = it },
            paddingHorizontal = 8.gdp,
            border = true,
            placeholder = {
                GPText(
                    text = "댓글을 작성해주세요.",
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextLightGray
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotEmpty()) {
                        onSendButtonClicked(text)
                        text = ""
                    }
                }
            ),
        )
        SpW(6.gdp)
        Box(
            modifier = Modifier
                .size(24.gdp)
                .background(
                    color = if (text.isNotEmpty()) GPColor.BackgroundFrameOrange
                        else GPColor.Transparent,
                    shape = CircleShape
                )
                .noRippleClickable {
                    if (text.isNotEmpty()) {
                        onSendButtonClicked(text)
                        text = ""
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(12.gdp),
                painter = painterResource(Res.drawable.icon_arrow_up),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    if (text.isNotEmpty()) GPColor.White
                    else GPColor.ButtonGray,
                )
            )
        }
        SpW(6.gdp)
        if (Define.userRole == TYPE_TEACHER && !isPassed) {
            GPButton(
                modifier = Modifier
                    .height(24.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = { onCertButtonClicked() },
            ) {
                GPText(
                    text = "채점",
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}