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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.request.GradeStudentRequest
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

private const val TAG = "TeacherCommunityDetailScreen"

@Composable
internal fun TeacherCommunityDetailScreen(
    modifier: Modifier = Modifier,
    postId: Long?
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

    val scrollState = rememberLazyListState()

    /////test/////
    var fullVideo by remember { mutableStateOf(false) }
    var fullImage by remember { mutableStateOf(false) }
    //////////////
    /////TEST///// TODO API
    var deleteConfirmDialog by remember { mutableStateOf(false) }
    //////////////

    LaunchedEffect(Unit) {
        GLog.d(TAG, "postId: $postId")
        communityStore.callPostingDetail(postId = postId ?: 0)
        communityStore.callCommentList(postId = postId ?: 0)
    }

    val endOfListReached by remember {
        derivedStateOf {
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItemsCount = scrollState.layoutInfo.totalItemsCount

            communityState.paginationInfo.hasNextPage && lastVisibleItem != null && lastVisibleItem.index >= totalItemsCount - 1
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached && communityState.paginationInfo.currentPage != communityState.paginationInfo.totalPage) {
            communityStore.loadNextPage(postId ?: 0, communityState.paginationInfo.currentPage + 1)
        }
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
            if (postId != null) {
                CommunityDetailInfoRow(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .height(76.gdp),
                    postingDetailInfo = communityState.postingDetailInfo
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (communityState.postingDetailInfo.questInfo.questType == "ROAD_MAP") {
                        VideoPlayer(
                            modifier = Modifier.fillMaxSize(),
                            videoPath = communityState.postingDetailInfo.fileUrl,
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
                                model = communityState.postingDetailInfo.fileUrl,
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
                        text = "댓글 " + communityState.paginationInfo.totalCount,
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.TextBlack
                    )
                }
                TeacherCommunityCommentColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    listState = scrollState,
                    commentList = communityState.commentList,
                    callLike = { commentId, onSuccess ->
                        communityStore.callCommentLike(CommentLikeRequest(commentId)) { onSuccess() }
                    },
                    callUnlike = { commentId, onSuccess ->
                        communityStore.callCommentUnlike(CommentLikeRequest(commentId)) { onSuccess() }
                    }
                )
                CommentInputRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onSendButtonClicked = { content ->
                        communityStore.callPostComment(
                            commentRequest = CommentRequest(
                                postId = postId,
                                content = content
                            ),
                            onSuccess = {
                                communityStore.callCommentList(postId = postId)
                            }
                        )
                    },
                    onCertButtonClicked = { gradeStore.onClickGradeButton() }
                )
            }
        }
    }

    with(deleteConfirmDialog) {
        if (this) {
            GPConfirmDialog(
                title = "",
                content = "삭제할까요?",
                onConfirmClicked = {
                    deleteConfirmDialog = false
                }, //TODO API
                onCancelClicked = { deleteConfirmDialog = false },
            )
        }
    }

    with(fullVideo) {
        if (this) {
            VideoWindowPlayer(
                videoPath = communityState.postingDetailInfo.fileUrl,
                dismiss = { fullVideo = false }
            )
        }
    }

    with(fullImage) {
        if (this) {
            ImageViewer(
                imagePath = communityState.postingDetailInfo.fileUrl,
                dismiss = { fullImage = false }
            )
        }
    }

    with(gradeState.gradeDialog) {
        if (this) {
            GradeDialog(
                questName = communityState.postingDetailInfo.questInfo.getQuestTitle(),

                onCloseButtonClicked = { gradeStore.dismissGradeDialog() },
                onConfirmButtonClicked = { star, isChecked ->
                    gradeStore.onClickConfirmButton(
                        star,
                        isChecked
                    )
                }
            )
        }
    }

    if (gradeState.successDialog) {
        GPAlertDialog(
            dismiss = {
                gradeStore.dismissSuccessDialog()
                gradeStore.dismissGradeDialog()

            },
            title = "학생 채점",
            content = "채점되었습니다!",
        )
    }


    with(gradeState.confirmDialog) {
        if (this) {
            GPConfirmDialog(
                title = "",
                content = "채점할까요?",
                onConfirmClicked = {
                    gradeStore.dismissConfirmDialog()
                    gradeStore.gradingStudent(
                        GradeStudentRequest(
                            questPostId = postId ?: 0,
                            isPass = true,
                            hasExtraPoints = gradeState.hasExtraPoints,
                            starPoint = gradeState.starPoint
                        ),
                    )
                },
                onCancelClicked = { gradeStore.dismissConfirmDialog() },
            )
        }
    }

    with(communityState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { communityStore.dismissErrorDialog() },
                title = "게시판 에러",
                content = this.message.orEmpty(),
            )
        }
    }

    with(gradeState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { gradeStore.dismissErrorDialog() },
                title = "학생 인증 에러",
                content = this.message.orEmpty(),
            )
        }
    }

    if (communityState.loading) {
        Loader()
    }
}
