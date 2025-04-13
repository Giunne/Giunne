package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.remote.response.convertType
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.presentation.certification.student.intent.VideoUploadStore
import com.project.giunne.common.presentation.certification.student.state.VideoUploadState
import com.project.giunne.common.presentation.common.picker.VideoPicker
import com.project.giunne.common.presentation.common.player.VideoWindowPlayer
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.content.EmptyItemList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.common.util.removeSpaceUrl

private const val TAG = "RoadMapCertScreen"
@Composable
fun RoadMapCertScreen(
    modifier: Modifier = Modifier,
    onCertButtonClicked: () -> Unit,
    videoUploadStore: VideoUploadStore,
    videoUploadState: VideoUploadState,
    checkProgressItem: StudentQuestInfo?,
    uploadProgressItem: StudentQuestInfo?,
    roadmapHistoryList: List<StudentQuestInfo>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            checkProgressItem != null -> {
                RoadmapCertBox(
                    modifier = Modifier
                        .background(
                            color = GPColor.White,
                            shape = RoundedCornerShape(16.gdp)
                        )
                        .fillMaxWidth()
                        .height(262.gdp),
                    roadmapLevel = checkProgressItem.trainingType.convertType(),
                    roadmapName = checkProgressItem.questName.replace(".", "단계 "),
                    video = videoUploadState.videoFile,
                    onUploadButtonClicked = {
                        videoUploadStore.onClickVideoUploadButton()
                    },
                    onCertButtonClicked = { onCertButtonClicked() },
                    onPlayButtonClicked = { videoUploadStore.onClickVideoPlayButton() },
                    onResetButtonClicked = { videoUploadStore.onClickVideoResetButton() },
                    dragAndDropFile = { file ->
                        if (file != null) videoUploadStore.initVideoFile(file)
                    }
                )
            }
            uploadProgressItem != null -> {
                RoadmapCertProgressBox(
                    modifier = Modifier
                        .background(
                            color = GPColor.White,
                            shape = RoundedCornerShape(16.gdp)
                        )
                        .fillMaxWidth()
                        .height(262.gdp),
                    roadmapLevel = uploadProgressItem.trainingType,
                    roadmapName = uploadProgressItem.questName,
                    progressText = "선생님이 확인중이에요!",
                    icon = {
                        AsyncImage(
                            modifier = Modifier.size(48.gdp),
                            model = IMAGE_BASE_URL + uploadProgressItem.thumbnailUrl?.removeSpaceUrl(),
                            contentDescription = null
                        )
                    },
                    percent = ((1 + uploadProgressItem.questStateInfo.currentApproveCount) * 2).toFloat() / (3 * uploadProgressItem.needApproveCount).toFloat(),
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .background(
                            color = GPColor.White,
                            shape = RoundedCornerShape(16.gdp)
                        )
                        .fillMaxWidth()
                        .height(262.gdp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GPText(
                            text = "선생님이 체크해줄거에요.",
                            textColor = GPColor.TextBlack,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                        SpH(10.gdp)
                        GPText(
                            text = "체크를 받으면 영상을 올릴 수 있어요!",
                            textColor = GPColor.TextBlack,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                    }
                }
            }
        }
        SpH(10.gdp)
        DoneListBox(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .fillMaxWidth()
                .padding(vertical = 8.gdp, horizontal = 4.gdp),
            content = {
                if (roadmapHistoryList.isEmpty()) {
                    EmptyItemList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        description = "완료된 내역이 아직 없습니다!"
                    )
                } else {
                    LazyColumn{
                        items(
                            count = roadmapHistoryList.size
                        ) {
                            RoadmapDoneListItemRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(68.gdp),
                                historyItem = roadmapHistoryList[it]
                            )
                            SpH(4.gdp)
                        }
                    }
                }
            }
        )
    }

    with(videoUploadState.videoPicker) {
        if (this) {
            VideoPicker { file ->
                if (file != null) videoUploadStore.initVideoFile(file)
                videoUploadStore.dismissVideoPicker()
            }
        }
    }

    with(videoUploadState.videoPlayer) {
        if (this) {
            VideoWindowPlayer(
                videoPath = videoUploadState.videoFile?.getPath() ?: "",
                dismiss = { videoUploadStore.dismissVideoPlayer() }
            )
        }
    }
}