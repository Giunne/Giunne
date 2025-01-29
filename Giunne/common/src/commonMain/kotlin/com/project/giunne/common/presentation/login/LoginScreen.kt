package com.project.giunne.common.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.presentation.login.LoginComponent.Companion.LOGIN_FAIL
import com.project.giunne.common.presentation.login.LoginComponent.Companion.NON_FAIL
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_lock
import com.project.giunne.icon_person
import com.project.giunne.image_logo_rb
import org.jetbrains.compose.resources.painterResource

private const val TAG = "LoginScreen"
@Composable
internal fun LoginScreen(
    component: LoginComponent,
    navigateSignup: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val loginFailEffect by component.loginFailEffect.collectAsState(NON_FAIL)

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLightGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpH(100.gdp)
            Image( // 로고 이미지
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp),
                painter = painterResource(Res.drawable.image_logo_rb),
                contentDescription = "Giunnae Logo"
            )
            Spacer(modifier = Modifier.height(32.gdp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                ) {
                    GPTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.gdp)
                            .background(color = GPColor.White),
                        shape = RoundedCornerShape(10.gdp),
                        textStyle = TextStyle(
                            color = GPColor.TextBlack,
                            fontSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium
                        ),
                        value = component.id,
                        onValueChange = { component.id = it },
                        border = true,
                        paddingHorizontal = 14.gdp,
                        placeholder = {
                            GPText(
                                text = "teacher",
                                textSize = 12.gsp,
                                fontFamily = GPFontFamily.Medium,
                                textColor = GPColor.TextLightGray
                            )
                        },
                        prefix = {
                            Image(
                                modifier = Modifier.size(14.gdp),
                                painter = painterResource(Res.drawable.icon_person),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = GPColor.ButtonGray)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        ),
                    )
                }
                SpH(20.gdp)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                ) {
                    GPTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.gdp)
                            .background(color = GPColor.White),
                        shape = RoundedCornerShape(10.gdp),
                        textStyle = TextStyle(
                            color = GPColor.TextBlack,
                            fontSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium
                        ),
                        value = component.pass,
                        onValueChange = { component.pass = it },
                        border = true,
                        visualTransformation = PasswordVisualTransformation(),
                        paddingHorizontal = 14.gdp,
                        placeholder = {
                            GPText(
                                text = "********",
                                textSize = 12.gsp,
                                fontFamily = GPFontFamily.Medium,
                                textColor = GPColor.TextLightGray
                            )
                        },
                        prefix = {
                            Image(
                                modifier = Modifier.size(14.gdp),
                                painter = painterResource(Res.drawable.icon_lock),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = GPColor.ButtonGray),
                                contentScale = ContentScale.FillHeight
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                component.onLoginButtonClick()
                            }
                        ),
                    )
                }
                SpH(20.gdp)
                GPButton(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .height(56.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = {
                        component.onLoginButtonClick()
                    },
                ) {
                    GPText(
                        text = "로그인",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.gdp)
                        .height(30.gdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    GPText(
                        modifier = Modifier
                            .noRippleClickable { navigateSignup() },
                        text = "아직 회원이 아니신가요?",
                        textSize = 12.gsp,
                        textColor = GPColor.MainOrangeColor,
                        fontFamily = GPFontFamily.Regular
                    )
                    GPText(
                        modifier = Modifier
                            .noRippleClickable {  },
                        text = "아이디 / 비밀번호 찾기",
                        textSize = 12.gsp,
                        textColor = GPColor.ButtonLightGray,
                        fontFamily = GPFontFamily.Regular
                    )
                }
            }
        }
    }
    with(loginFailEffect) {
        if (this == LOGIN_FAIL) {
            GPAlertDialog(
                dismiss = { component.dismissDialog() },
                title = "로그인 실패",
                content = "로그인에 실패하였습니다. 확인해주세요.",
            )
        }
    }
}

