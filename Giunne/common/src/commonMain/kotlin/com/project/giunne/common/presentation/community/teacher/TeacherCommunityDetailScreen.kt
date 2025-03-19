package com.project.giunne.common.presentation.community.teacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import coil3.compose.AsyncImage
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.player.ImageViewer
import com.project.giunne.common.presentation.common.player.VideoPlayer
import com.project.giunne.common.presentation.common.player.VideoWindowPlayer
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.content.CommentInputRow
import com.project.giunne.common.presentation.community.content.CommunityDetailInfoRow
import com.project.giunne.common.presentation.community.content.GradeDialog
import com.project.giunne.common.presentation.community.content.TeacherCommunityCommentColumn
import com.project.giunne.common.presentation.community.student.dummy.commentTestList
import com.project.giunne.common.presentation.community.student.intent.CommunityStore
import com.project.giunne.common.presentation.community.student.intent.GradeStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.ZoomStore
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.common.util.onZoomEvent
import com.project.giunne.common.util.rememberZoomState
import com.project.giunne.icon_expand
import com.project.giunne.image_loader_1
import org.jetbrains.compose.resources.painterResource

private const val TAG = "TeacherCommunityDetailScreen"
@Composable
internal fun TeacherCommunityDetailScreen(
    modifier: Modifier = Modifier,
    questUploadInfo: QuestUploadInfo?
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val zoomStore = remember { ZoomStore() }
    val zoomUiState by zoomStore.uiState.collectAsState()

    val gradeStore = remember { GradeStore() }
    val gradeState by gradeStore.uiState.collectAsState()

    val communityStore = remember { CommunityStore() }
    val communityState by communityStore.uiState.collectAsState()

    /////test/////
    var fullVideo by remember { mutableStateOf(false) }
    var fullImage by remember { mutableStateOf(false) }
    //////////////

    LaunchedEffect(Unit) {
        communityStore.callPostingDetailList(
            playerId = questUploadInfo?.playerInfo?.id?.toLong() ?: 0,
            questId = questUploadInfo?.id?.toLong() ?: 0
        )
//        communityStore.callPostingDetail(
//            postId =
//        )
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (questUploadInfo != null && communityState.postingDetailListInfo.postInfoList.isNotEmpty()) {
                CommunityDetailInfoRow(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .height(76.gdp),
                    postingDetailListInfo = communityState.postingDetailListInfo
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (communityState.postingDetailListInfo.questInfo.questType == "ROAD_MAP") {
                        VideoPlayer(
                            modifier = Modifier.fillMaxSize(),
                            videoPath = communityState.postingDetailListInfo.postInfoList.last().fileUrl, //TODO API
                            onFullScreenClicked = { fullVideo = true }
                        )
                    } else {
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val state = rememberZoomState(zoomStore, constraints)

                            AsyncImage( // TODO API
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(12.gdp))
                                    .fillMaxSize()
                                    .graphicsLayer(
                                        scaleX = zoomUiState.scale,
                                        scaleY = zoomUiState.scale,
                                        translationX = zoomUiState.offsetX,
                                        translationY = zoomUiState.offsetY
                                    )
                                    .transformable(state)
                                    .onZoomEvent(
                                        scope = scope,
                                        state = state,
                                        onSingleTapEvent = {}
                                    ),
                                model = communityState.postingDetailListInfo.postInfoList.last().fileUrl,
                                placeholder = painterResource(Res.drawable.image_loader_1),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }

                        GPIconButton(
                            modifier = Modifier
                                .padding(10.gdp)
                                .size(28.gdp)
                                .align(Alignment.BottomEnd),
                            icon = {
                                Image(
                                    modifier = Modifier.size(18.gdp),
                                    painter = painterResource(Res.drawable.icon_expand),
                                    contentDescription = null,
                                )
                            },
                            normalColor = GPColor.White,
                            pressColor = GPColor.ButtonPressWhite,
                            onClick = {
                                fullImage = true
                            },
                        )
                    }
                }
                SpH(4.gdp)
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .height(38.gdp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPText(
                        text = "댓글 " + commentTestList.size.toString(),
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.TextBlack
                    )
                }
                TeacherCommunityCommentColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    commentList = commentTestList
                )
                CommentInputRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onSendButtonClicked = {  }, // TODO API
                    onCertButtonClicked = { gradeStore.onClickGradeButton() }
                )
            }
        }
    }

    with(fullVideo) {
        if (this) {
            VideoWindowPlayer(
                videoPath = communityState.postingDetailListInfo.postInfoList.last().fileUrl,
                dismiss = { fullVideo = false }
            )
        }
    }

    with(fullImage) {
        if (this) {
            ImageViewer(
                imagePath = communityState.postingDetailListInfo.postInfoList.last().fileUrl,
                dismiss = { fullImage = false }
            )
        }
    }

    with(gradeState.gradeDialog) {
        if (this) {
            GradeDialog(
                questName = communityState.postingDetailListInfo.questInfo.getQuestTitle(),
                onCloseButtonClicked = { gradeStore.dismissGradeDialog() },
                onConfirmButtonClicked = { star, isChecked ->
                    gradeStore.onClickConfirmButton()
                }
            )
        }
    }

    with(gradeState.confirmDialog) {
        if (this) {
            GPConfirmDialog(
                title = "",
                content = "채점할까요?",
                onConfirmClicked = {
                    gradeStore.dismissConfirmDialog()
                    gradeStore.dismissGradeDialog()
                }, //TODO API
                onCancelClicked = { gradeStore.dismissConfirmDialog() },
            )
        }
    }

    with(communityState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { communityStore.dismissErrorDialog() },
                title = "인증 화면 에러",
                content = this.message.orEmpty(),
            )
        }
    }

    if (communityState.loading) {
        Loader()
    }
}
