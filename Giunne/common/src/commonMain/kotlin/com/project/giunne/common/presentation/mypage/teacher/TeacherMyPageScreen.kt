package com.project.giunne.common.presentation.mypage.teacher

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.data.util.TokenHandler
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.mypage.student.content.MyPageStudentInfoColumn
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

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
    val scope = rememberCoroutineScope()

    val myPageState by component.uiState.collectAsState()

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
            //TODO TEST
            MyPageStudentInfoColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp),
                onClickLogOut = { component.onClickLogoutButton() }
            )
            //TODO TEST (바꿔야 하는 UI)
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
