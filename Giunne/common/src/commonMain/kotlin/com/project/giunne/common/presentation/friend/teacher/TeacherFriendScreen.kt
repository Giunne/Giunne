package com.project.giunne.common.presentation.friend.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.certification.student.content.PageSelectRow
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.friend.content.StudentRoadMapDialog
import com.project.giunne.common.presentation.friend.content.TeacherFriendItemRow
import com.project.giunne.common.presentation.friend.intent.FriendStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp

private const val TAG = "TeacherFriendScreen"
@Composable
internal fun TeacherFriendScreen(
    component: TeacherFriendComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val friendStore = remember { FriendStore() }
    val friendState by friendStore.uiState.collectAsState()

    LaunchedEffect(Unit) {
        friendStore.getFriendsList(
            recreationId = AvatarUtil.uiState.value.recreationId.toLong(),
            id = AvatarUtil.uiState.value.id
        )
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageSelectRow(
                modifier = Modifier
                    .padding(horizontal = 16.gdp, vertical = 10.gdp)
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(64.gdp)
                    .padding(horizontal = 8.gdp),
                page = friendState.pageType,
                onRoadmapClicked = {
                    friendStore.setRoadMapId(CertPage.RoadMap, 1)
                },
                onRunningClicked = {
                    friendStore.setRoadMapId(CertPage.Running, 2)
                }
            )

            SpH(8.gdp)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .background(color = GPColor.BackgroundLightGray),
            ) {
                items(
                    count = friendState.friendsList.size
                ) {
                    TeacherFriendItemRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.gdp)
                            .padding(horizontal = 16.gdp),
                        friendInfo = friendState.friendsList[it],
                        onClick = { playerId ->
                            friendStore.getSpecificStudentCourse(1, playerId)
                        }
                    )
                }
            }
        }
    }

    with(friendState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { friendStore.dismissErrorDialog() },
                title = "에러",
                content = friendState.error?.message.orEmpty(),
            )
        }
    }

    if(friendState.loading) {
        Loader()
    }
}
