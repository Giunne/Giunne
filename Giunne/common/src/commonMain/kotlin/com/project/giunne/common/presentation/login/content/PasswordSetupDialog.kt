package com.project.giunne.common.presentation.login.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_STUDENT
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.presentation.signup.content.SignupInputColumn
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.common.util.isValidPassword
import kotlinx.serialization.json.JsonNull.content
import java.awt.SystemColor.text

@Composable
fun PasswordSetupDialog(
    onConfirmClicked: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var passText by remember { mutableStateOf("") }
    var passConfText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onCancelClicked() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            modifier = Modifier
                .background(GPColor.White, RoundedCornerShape(12.gdp))
                .width(320.gdp)
                .wrapContentHeight()
                .padding(vertical = 10.gdp, horizontal = 16.gdp),
            shape = RoundedCornerShape(12.gdp)
        ) {
            Column(
                modifier = Modifier
                    .background(GPColor.White)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.gdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GPText(
                        text = "비밀번호 변경",
                        textSize = 16.gsp,
                        textColor = GPColor.ButtonBlack,
                        fontFamily = GPFontFamily.Regular
                    )
                }
                SignupInputColumn(
                    titleText = "비밀번호",
                    text = passText,
                    onTextChanged = { passText = it },
                    sideContent = {
                        GPText(
                            text = "영어, 특수문자, 숫자를 포함한 8자리",
                            textSize = 10.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.ButtonGray
                        )
                    },
                    focusManager = focusManager,
                )
                SpH(10.gdp)
                SignupInputColumn(
                    titleText = "비밀번호 확인",
                    text = passConfText,
                    onTextChanged = { passConfText = it },
                    focusManager = focusManager,
                )
                Row(
                    modifier = Modifier
                        .height(68.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonLightGray,
                        pressColor = GPColor.ButtonPressLightGray,
                        hoverColor = GPColor.ButtonHoverLightGray,
                        onClick = { onCancelClicked() },
                    ) {
                        GPText(
                            text = "닫기",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                    SpW(10.gdp)
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = {
                            passwordValidCheck(
                                passText = passText,
                                passConfText = passConfText,
                            ) {
                                if (it.isEmpty()) {
                                    onConfirmClicked(passConfText)
                                }
                                errorMessage = it
                            }
                        },
                    ) {
                        GPText(
                            text = "변경",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }
            }
        }
    }

    with(errorMessage) {
        if (this.isNotEmpty()) {
            GPAlertDialog(
                title = "변경 에러",
                content = errorMessage,
                dismiss = { errorMessage = "" }
            )
        }
    }
}

private fun passwordValidCheck(
    passText: String,
    passConfText: String,
    callback: (String) -> Unit
) {
    callback(when {
        passText.isEmpty() -> "비밀번호를 입력해야 합니다."
        passConfText.isEmpty() -> "비밀번호 확인을 입력해야 합니다."
        !passText.isValidPassword() -> "비밀번호 형식이 잘못되었습니다.\n(영어, 숫자를 포함한 8자리)"
        passText != passConfText -> "비밀번호, 비밀번호 확인이 서로 다릅니다."
        else -> ""
    })
}