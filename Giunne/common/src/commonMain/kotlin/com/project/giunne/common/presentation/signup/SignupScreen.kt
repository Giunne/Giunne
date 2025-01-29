package com.project.giunne.common.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.text.GPTitleText
import com.project.giunne.common.presentation.common.topbar.GPTopBar
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_STUDENT
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.presentation.signup.content.SchoolSearchDialog
import com.project.giunne.common.presentation.signup.content.SignupInputColumn
import com.project.giunne.common.presentation.signup.intent.InfoStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "SignupScreen"
@Composable
internal fun SignupScreen(
    component: SignupComponent,
    onClickBackButton: () -> Unit,
    signupType: String,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val infoStore = remember { InfoStore(scope) }
    val infoState by infoStore.infoState

    val scrollState = rememberScrollState()

    ///test///
    var searchList by remember { mutableStateOf(mutableListOf("aaa", "aaaa", "sdgas", "12412rgd", "df", "aaa", "aaaa", "sdgas", "12412rgd", "df")) }
    var loading by remember { mutableStateOf(false) }
    //////////

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(GPColor.BackgroundLightGray)
                    .align(Alignment.TopCenter)
                    .padding(bottom = 72.gdp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GPTopBar(
                    titleText = "회원가입",
                    onBackButtonClicked = onClickBackButton
                )
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(GPColor.BackgroundLightGray)
                        .verticalScroll(scrollState)
                        .padding(bottom = 16.gdp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(52.gdp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        GPTitleText(
                            modifier = Modifier.align(Alignment.Center),
                            text = "기운내 프로젝트에 오신것을 환영합니다! 👏🏻",
                            textSize = 14.gsp,
                            textColor = GPColor.TextBlack,
                            fontFamily = GPFontFamily.Medium
                        )
                    }
                    SignupInputColumn(
                        titleText = "아이디",
                        text = infoState.idText,
                        onTextChanged = { infoStore.onIdTextChanged(it) },
                        sideContent = {
                            GPButton(
                                modifier = Modifier
                                    .width(80.gdp)
                                    .height(30.gdp),
                                shape = RoundedCornerShape(8.gdp),
                                normalColor = GPColor.ButtonOrange,
                                pressColor = GPColor.ButtonPressOrange,
                                hoverColor = GPColor.ButtonHoverOrange,
                                onClick = {  },
                            ) {
                                GPText(
                                    text = "중복확인",
                                    textSize = 12.gsp,
                                    fontFamily = GPFontFamily.Bold,
                                    textColor = GPColor.White
                                )
                            }
                        },
                        focusManager = focusManager,
                    )
                    SpH(10.gdp)
                    SignupInputColumn(
                        titleText = "비밀번호",
                        text = infoState.passText,
                        onTextChanged = { infoStore.onPassTextChanged(it) },
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
                        text = infoState.passConfText,
                        onTextChanged = { infoStore.onPassConfTextChanged(it) },
                        focusManager = focusManager,
                    )
                    SpH(10.gdp)
                    if (signupType == TYPE_STUDENT) {
                        SignupInputColumn(
                            titleText = "코드 입력",
                            text = infoState.codeText,
                            onTextChanged = { infoStore.onCodeTextChanged(it) },
                            sideContent = {
                                GPText(
                                    text = "선생님이 알려주신 코드를 입력해주세요.",
                                    textSize = 10.gsp,
                                    fontFamily = GPFontFamily.Medium,
                                    textColor = GPColor.MainOrangeColor
                                )
                            },
                            focusManager = focusManager,
                        )
                    } else if (signupType == TYPE_TEACHER) {
                        SignupInputColumn(
                            titleText = "학교 선택",
                            text = infoState.schoolText,
                            onTextChanged = {  },
                            focusable = false,
                            hintText = "검색해주세요.",
                            sideContent = {
                                GPButton(
                                    modifier = Modifier
                                        .width(86.gdp)
                                        .height(30.gdp),
                                    shape = RoundedCornerShape(8.gdp),
                                    normalColor = GPColor.ButtonOrange,
                                    pressColor = GPColor.ButtonPressOrange,
                                    hoverColor = GPColor.ButtonHoverOrange,
                                    onClick = {
                                        scope.launch {
                                            loading = true
                                            delay(2000)
                                            infoStore.onClickSchoolSearchButton()
                                            loading = false
                                        }
                                    },
                                ) {
                                    GPText(
                                        text = "학교 찾기",
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Bold,
                                        textColor = GPColor.White
                                    )
                                }
                            },
                            focusManager = focusManager,
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
            }
            GPButton(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth()
                    .height(56.gdp)
                    .align(Alignment.BottomCenter),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = {  }, // TODO API
            ) {
                GPText(
                    text = "회원가입",
                    textSize = 16.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }

        with(infoState.schoolSearchDialog) {
            if (this) {
                SchoolSearchDialog(
                    dismiss = { infoStore.dismissSchoolSearchDialog() },
                    onClickConfirmButton = {
                        infoStore.onSchoolSelected(it)
                        infoStore.dismissSchoolSearchDialog()
                    },
                    onClickSearchButton = { searchText ->
                        searchList = searchList.filter {
                            searchText == it
                        }.toMutableList()
                    },
                    searchList = searchList
                )
            }
        }

        if (loading) {
            Loader()
        }
    }
}

