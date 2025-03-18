package com.project.giunne.common.presentation.friend.teacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.friend.content.StudentFriendItemRow
import com.project.giunne.common.presentation.friend.content.TeacherFriendItemRow
import com.project.giunne.common.presentation.friend.dummy.friendList
import com.project.giunne.common.presentation.friend.intent.FriendStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

private const val TAG = "TeacherFriendScreen"
@Composable
internal fun TeacherFriendScreen(
    component: TeacherFriendComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val friendStore = remember { FriendStore() }
    val friendState by friendStore.uiState.collectAsState()

    LaunchedEffect(Unit) {
        friendStore.getFriendsList(
//            recreationId = AvatarUtil.uiState.value.recreationId.toLong(),
            recreationId = 18, // TODO API
            id = AvatarUtil.uiState.value.id
        )
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GPColor.BackgroundLightGray)
        ) {
            items(
                count = friendState.friendsList.size
            ) {
                // TODO TeacherFriendItemRow
//                TeacherFriendItemRow(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(64.gdp)
//                        .padding(horizontal = 16.gdp),
//                    friendInfo = friendList[it]
//                )
                StudentFriendItemRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.gdp)
                        .padding(horizontal = 16.gdp),
                    friendInfo = friendState.friendsList[it]
                )
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
