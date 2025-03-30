package com.project.giunne.common.presentation.mypage.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.data.util.TokenHandler
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.mypage.common.MyPageSettingInfo
import com.project.giunne.common.presentation.mypage.student.content.MyPageStudentInfoColumn
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_next
import org.jetbrains.compose.resources.painterResource

private const val TAG = "TeacherMyPageScreen"
@Composable
internal fun TeacherMyPageScreen(
    component: TeacherMyPageComponent,
    modifier: Modifier = Modifier,
    navigateToShop: ()-> Unit,
    navigateToGacha: ()-> Unit,
    onLogout: () -> Unit
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val myPageState by component.uiState.collectAsState()

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
                    .background(GPColor.BackgroundLightGray),
                verticalArrangement = Arrangement.spacedBy(16.gdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyPageStudentInfoColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.gdp),
                ) {
                    MyPageSettingInfo(
                        title = "학교",
                        content = {
                            GPText(
                                text = myPageState.avatarInformation.schoolName,
                                fontFamily = GPFontFamily.Regular,
                                textSize = 12.gsp
                            )
                        }
                    )
                    HorizontalDivider(
                        color = GPColor.BackgroundGray_F6F6F6
                    )
                    MyPageSettingInfo(
                        title = "로그아웃",
                        color = GPColor.Red,
                        content = {
                            Icon(
                                painter = painterResource(Res.drawable.icon_next),
                                contentDescription = "로그아웃",
                                tint = GPColor.Red
                            )
                        },
                        onClick = {
                            component.onClickLogoutButton()
                        }
                    )
                }
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

    with(myPageState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { component.dismissErrorDialog() },
                title = "내 정보 화면 에러",
                content = myPageState.error?.message.orEmpty(),
            )
        }
    }
}
