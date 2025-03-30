package com.project.giunne.common.presentation.community.content

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
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.serialization.json.JsonNull.content
import java.awt.SystemColor.text

@Composable
fun SendLikeDialog(
    commentId: Long,
    onConfirmClicked: (String, String) -> Unit,
    onCancelClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var rewardPoint by remember { mutableStateOf("") }
    var rewardExp by remember { mutableStateOf("") }

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
                        text = "좋아요 보상 지급",
                        textSize = 17.gsp,
                        textColor = GPColor.ButtonBlack,
                        fontFamily = GPFontFamily.Regular
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.gdp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
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
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) },
                        ),
                        placeholder = {
                            GPText(
                                text = "보상 코인을 입력해주세요.",
                                textColor = GPColor.TextLightGray,
                                textSize = 12.gsp,
                                fontFamily = GPFontFamily.Medium
                            )
                        },
                        value = rewardPoint,
                        onValueChange = {
                            rewardPoint = it
                        },
                        border = true,
                        suffix = {
                            GPText(
                                text = "코인",
                                textSize = 12.gsp,
                                textColor = GPColor.MainOrangeColor,
                                fontFamily = GPFontFamily.Bold
                            )
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.gdp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
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
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onConfirmClicked(rewardPoint, rewardExp) },
                        ),
                        placeholder = {
                            GPText(
                                text = "보상 경험치를 입력해주세요.",
                                textColor = GPColor.TextLightGray,
                                textSize = 12.gsp,
                                fontFamily = GPFontFamily.Medium
                            )
                        },
                        value = rewardExp,
                        onValueChange = {
                            rewardExp = it
                        },
                        border = true,
                        suffix = {
                            GPText(
                                text = "exp",
                                textSize = 12.gsp,
                                textColor = GPColor.MainOrangeColor,
                                fontFamily = GPFontFamily.Bold
                            )
                        }
                    )
                }
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
                        onClick = { onConfirmClicked(rewardPoint, rewardExp) },
                    ) {
                        GPText(
                            text = "확인",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }
            }
        }
    }
}