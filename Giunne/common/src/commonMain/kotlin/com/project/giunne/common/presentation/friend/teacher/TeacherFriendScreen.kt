package com.project.giunne.common.presentation.friend.teacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.character_cat_level_1
import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.presentation.certification.student.content.PageSelectRow
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.friend.content.CheckModifyPoint
import com.project.giunne.common.presentation.friend.content.ModifyExpDialog
import com.project.giunne.common.presentation.friend.content.ModifyPointDialog
import com.project.giunne.common.presentation.friend.content.StudentRoadMapDialog
import com.project.giunne.common.presentation.friend.content.TeacherFriendItemRow
import com.project.giunne.common.presentation.friend.content.TeacherModifyStudentPoint
import com.project.giunne.common.presentation.friend.intent.FriendStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define.playerId
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.serialization.json.JsonNull.content
import org.jetbrains.compose.resources.painterResource

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
    var isPointModifyCheck by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        friendStore.getStudentList(
            recreationId = AvatarUtil.uiState.value.recreationId.toLong(),
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

            CheckModifyPoint(
                modifier = Modifier.align(Alignment.End),
                isChecked = isPointModifyCheck,
                onCheckedChanged = {
                    isPointModifyCheck = it
                }
            )

            SpH(8.gdp)

            if (friendState.friendsList.isNotEmpty()) {
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
                            isPointModifyCheck = isPointModifyCheck,
                            onClick = { playerId ->
                                friendStore.getSpecificStudentCourse(1, playerId)
                            }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Row {
                                    GPText(
                                        text = friendState.friendsList[it].level.toString(),
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.MainOrangeColor
                                    )
                                    GPText(
                                        text = " 레벨",
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.TextBlack
                                    )
                                }
                                SpH(2.gdp)
                                Row {
                                    GPText(
                                        text = friendState.friendsList[it].exp.toString(),
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.MainOrangeColor
                                    )
                                    GPText(
                                        text = " exp",
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.TextBlack
                                    )
                                }
                                SpH(2.gdp)
                                Row {
                                    GPText(
                                        text = friendState.friendsList[it].point.toString(),
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.MainOrangeColor
                                    )
                                    GPText(
                                        text = " 코인",
                                        textSize = 12.gsp,
                                        fontFamily = GPFontFamily.Medium,
                                        textColor = GPColor.TextBlack
                                    )
                                }
                            }
                            if (isPointModifyCheck) {
                                SpW(8.gdp)
                                Row {
                                    GPButton(
                                        modifier = Modifier
                                            .height(40.gdp)
                                            .width(52.gdp),
                                        normalColor = GPColor.ButtonBlack,
                                        pressColor = GPColor.ButtonPressBlack,
                                        hoverColor = GPColor.ButtonHoverBlack,
                                        horizontalPadding = 8.gdp,
                                        onClick = { friendStore.selectPointAvatar(
                                            friendState.friendsList[it]
                                        ) },
                                    ) {
                                        GPText(
                                            text = "코인",
                                            textSize = 12.gsp,
                                            fontFamily = GPFontFamily.Bold,
                                            textColor = GPColor.White
                                        )
                                    }
                                    SpW(8.gdp)
                                    GPButton(
                                        modifier = Modifier
                                            .height(40.gdp)
                                            .width(60.gdp),
                                        normalColor = GPColor.ButtonBlack,
                                        pressColor = GPColor.ButtonPressBlack,
                                        hoverColor = GPColor.ButtonHoverBlack,
                                        horizontalPadding = 8.gdp,
                                        onClick = {
                                            friendStore.selectExpAvatar(
                                                friendState.friendsList[it]
                                            )
                                        },
                                    ) {
                                        GPText(
                                            text = "경험치",
                                            textSize = 12.gsp,
                                            fontFamily = GPFontFamily.Bold,
                                            textColor = GPColor.White
                                        )
                                    }
                                }
//                                TeacherModifyStudentPoint(
//                                    point = friendState.friendsList[it].pointBuffer.toString(),
//                                    onPointChanged = { point ->
//                                        friendStore.modifyStudentPointLocal(
//                                            index = it,
//                                            point = point
//                                        )
//                                    }
//                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(64.gdp),
                            painter = painterResource(Res.drawable.character_cat_level_1),
                            contentDescription = null
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GPText(
                                text = "로드맵에 속한 학생들이 없어요.",
                                textSize = 14.gsp,
                                fontFamily = GPFontFamily.Bold,
                                textColor = GPColor.TextBlack
                            )
                        }
                    }
                }
            }

//            if (isPointModifyCheck) {
//                GPButton(
//                    modifier = Modifier
//                        .padding(16.gdp)
//                        .fillMaxWidth()
//                        .height(48.gdp),
//                    normalColor = GPColor.ButtonOrange,
//                    pressColor = GPColor.ButtonPressOrange,
//                    hoverColor = GPColor.ButtonHoverOrange,
//                    onClick = { friendStore.showModifyCheckDialog() },
//                ) {
//                    GPText(
//                        text = "수정하기",
//                        textSize = 16.gsp,
//                        fontFamily = GPFontFamily.Bold,
//                        textColor = GPColor.White
//                    )
//                }
//            }
        }
    }

//    if (friendState.showModifyCheckDialog) {
//        GPConfirmDialog(
//            title = "학생 포인트 수정",
//            content = "변경된 학생들의 포인트를 수정할까요?",
//            onConfirmClicked = {
//                friendStore.dismissModifyCheckDialog()
//                friendStore.modifyStudentPoint()
//            },
//            onCancelClicked = { friendStore.dismissModifyCheckDialog() },
//        )
//    }
    with (friendState.selectedPointAvatar) {
        if (this != null) {
            ModifyPointDialog(
                name = this.nickname,
                initialPoint = this.point.toString(),
                onConfirmClicked = { modifiedPoint ->
                    friendStore.modifyStudentPoint(
                        studentPointRequest = StudentPointRequest(this.id, modifiedPoint.toInt())
                    )
                    friendStore.dismissModifyPointDialog()
                },
                onCancelClicked = { friendStore.dismissModifyPointDialog() },
            )
        }
    }

    with (friendState.selectedExpAvatar) {
        if (this != null) {
            ModifyExpDialog(
                name = this.nickname,
                onConfirmClicked = { rewardExp ->
                    friendStore.callModifyStudentExp(
                        studentExpRequest = StudentExpRequest(this.id, rewardExp.toInt())
                    )
                    friendStore.dismissModifyExpDialog()
                },
                onCancelClicked = { friendStore.dismissModifyExpDialog() },
            )
        }
    }

    if (friendState.showModifySuccessDialog) {
        GPAlertDialog(
            title = "학생 포인트 수정",
            content = "포인트 수정이 완료되었습니다!",
            dismiss = { friendStore.dismissModifySuccessDialog() }
        )
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

    if (friendState.showStudentCourse) {
        StudentRoadMapDialog(
            pageType = friendState.pageType,
            questInfoList = friendState.courseMap.values.flatten(),
            onDismiss = {
                friendStore.dismissStudentRoadMapDialog()
            }
        )
    }

    if(friendState.loading) {
        Loader()
    }
}
