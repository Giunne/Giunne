package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.project.giunne.common.presentation.certification.student.dummy.roadmapDoneList
import com.project.giunne.common.presentation.certification.student.intent.VideoUploadStore
import com.project.giunne.common.presentation.common.picker.VideoPicker
import com.project.giunne.common.presentation.common.player.VideoWindowPlayer
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.roadcon_3_beast
import org.jetbrains.compose.resources.painterResource

private const val TAG = "RoadMapCertScreen"
@Composable
fun RoadMapCertScreen(
    modifier: Modifier = Modifier,
    onCertButtonClicked: () -> Unit,
    step: Int?
) {
    val scope = rememberCoroutineScope()

    val videoUploadStore = remember { VideoUploadStore(scope) }
    val videoUploadState by videoUploadStore.state.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (step != null) {
            RoadmapCertProgressBox(
                modifier = Modifier
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(262.gdp),
                roadmapLevel = "3단계",
                roadmapName = "비스트",
                progressText = "선생님이 확인중이에요!",
                icon = {
                    Image(
                        modifier = Modifier.size(48.gdp),
                        painter = painterResource(Res.drawable.roadcon_3_beast),
                        contentDescription = null
                    )
                },
                step = step,
            )
        } else {
            RoadmapCertBox(
                modifier = Modifier
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(262.gdp),
                roadmapLevel = "3단계", /* TODO API */
                roadmapName = "비스트",
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
                        count = roadmapDoneList.size
                    ) {
                        RoadmapDoneListItemRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(68.gdp),
                            doneItem = roadmapDoneList[it]
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