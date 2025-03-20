package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.remote.response.convertType
import com.project.giunne.common.presentation.certification.student.intent.VideoUploadStore
import com.project.giunne.common.presentation.certification.student.state.CertProgress
import com.project.giunne.common.presentation.common.picker.VideoPicker
import com.project.giunne.common.presentation.common.player.VideoWindowPlayer
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.roadcon_3_beast
import org.jetbrains.compose.resources.painterResource

private const val TAG = "RoadMapCertScreen"
@Composable
fun RoadMapCertScreen(
    modifier: Modifier = Modifier,
    onCertButtonClicked: () -> Unit,
    roadmapProgressList: List<StudentQuestInfo>,
    roadmapHistoryList: List<StudentQuestInfo>,
) {
    val scope = rememberCoroutineScope()

    val videoUploadStore = remember { VideoUploadStore(scope) }
    val videoUploadState by videoUploadStore.state.collectAsState()

    val checkProgressItem = roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code }
    val uploadProgressItem = roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code }

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
                    }, /* TODO API */
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
                        Image(
                            modifier = Modifier.size(48.gdp),
                            painter = painterResource(Res.drawable.roadcon_3_beast), // TODO 썸네일 파라미터 나오면
                            contentDescription = null
                        )
                    },
                    percent = ((1 + uploadProgressItem.currentApproveCount) * 2).toFloat() / (3 * uploadProgressItem.needApproveCount).toFloat(),
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