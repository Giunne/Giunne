package com.project.giunne.common.presentation.mypage.student

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.data.util.TokenHandler
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.charactor.GPMainCharacter
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.mypage.student.content.MyPageCharacter
import com.project.giunne.common.presentation.mypage.student.content.MyPageStudentInfoColumn
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

private const val TAG = "StudentMyPageScreen"
@Composable
internal fun StudentMyPageScreen(
    component: StudentMyPageComponent,
    modifier: Modifier = Modifier,
    navigateToShop: () -> Unit,
    navigateToGacha: () -> Unit,
    onLogout: () -> Unit
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val myPageState by component.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (Define.playerId != 0L) {
            component.getRecreationList(Define.playerId)
        }
    }
    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding()
    ) {
        if (Define.playerId != 0L && myPageState.isLoading) {
            Loader()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GPColor.BackgroundLightGray)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.gdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GPMainCharacter(
                    modifier = Modifier.size(256.gdp),
                    wearingItems = myPageState.userInfo.wearingItems
                )

                MyPageCharacter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.gdp),
                    wearingItems = myPageState.userInfo.wearingItems,
                    level = myPageState.userInfo.level,
                    percent = myPageState.userInfo.exp / myPageState.userInfo.needExp.toFloat()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.gdp)
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.gdp),
                        normalColor = GPColor.ButtonLightGray,
                        pressColor = GPColor.ButtonPressLightGray,
                        hoverColor = GPColor.ButtonHoverLightGray,
                        onClick = {
                            navigateToGacha()
                        },
                    ) {
                        GPText(
                            text = "뽑기",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.gdp))
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = {
                            navigateToShop()
                        },
                    ) {
                        GPText(
                            text = "꾸미기",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }

                MyPageStudentInfoColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.gdp),
                    onClickLogOut = { component.onClickLogoutButton() }
                )
                Spacer(modifier = Modifier.height(16.gdp))
            }
        }
    }

    with(myPageState.logoutDialog) {
        if (this) {
            GPConfirmDialog(
                title = "로그아웃",
                content = "로그아웃 할까요?",
                onConfirmClicked = {
                    component.dismissLogoutDialog()
                    TokenHandler.callLogout()
                    onLogout()
                },
                onCancelClicked = { component.dismissLogoutDialog() },
            )
        }
    }
}