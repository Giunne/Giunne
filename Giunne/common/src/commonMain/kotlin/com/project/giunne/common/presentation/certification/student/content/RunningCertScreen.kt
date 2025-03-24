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
import androidx.compose.ui.graphics.ColorFilter
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.presentation.certification.student.dummy.runningDoneList
import com.project.giunne.common.presentation.certification.student.intent.ImageUploadStore
import com.project.giunne.common.presentation.certification.student.state.CertProgress
import com.project.giunne.common.presentation.common.picker.ImagePicker
import com.project.giunne.common.presentation.common.player.ImageViewer
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_running
import kotlinx.serialization.json.JsonNull.content
import org.jetbrains.compose.resources.painterResource

private const val TAG = "RunningCertScreen"
@Composable
fun RunningCertScreen(
    modifier: Modifier = Modifier,
    onCertButtonClicked: () -> Unit,
    runningProgressList: List<StudentQuestInfo>,
    runningHistoryList: List<StudentQuestInfo>,
) {
    val scope = rememberCoroutineScope()

    val imageUploadStore = remember { ImageUploadStore(scope) }
    val imageUploadState by imageUploadStore.state.collectAsState()

    val checkProgressItem = runningProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code }
    val uploadProgressItem = runningProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            checkProgressItem != null -> {
                RunningCertBox(
                    modifier = Modifier
                        .background(
                            color = GPColor.White,
                            shape = RoundedCornerShape(16.gdp)
                        )
                        .fillMaxWidth()
                        .height(262.gdp),
                    weekText = checkProgressItem.questName, /* TODO API */
                    image = imageUploadState.imageFile,
                    onUploadButtonClicked = {
                        imageUploadStore.onClickImageUploadButton()
                    }, /* TODO API */
                    onCertButtonClicked = { onCertButtonClicked() },
                    onExpandButtonClicked = { imageUploadStore.onClickImageExpandButton() },
                    onResetButtonClicked = { imageUploadStore.onClickImageResetButton() },
                    dragAndDropFile = { file ->
                        if (file != null) imageUploadStore.initImageFile(file)
                    }
                )
            }
            uploadProgressItem != null -> {
                RunningCertProgressBox(
                    modifier = Modifier
                        .background(
                            color = GPColor.White,
                            shape = RoundedCornerShape(16.gdp)
                        )
                        .fillMaxWidth()
                        .height(262.gdp),
                    weekText = uploadProgressItem.questName,
                    progressText = "선생님이 확인중이에요!",
                    icon = {
                        Image(
                            modifier = Modifier.size(40.gdp),
                            painter = painterResource(Res.drawable.icon_running),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(GPColor.ButtonBlack)
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
                            text = "체크를 받으면 사진을 올릴 수 있어요!",
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
                        count = runningHistoryList.size
                    ) {
                        RunningDoneListItemRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(68.gdp),
                            historyItem = runningHistoryList[it]
                        )
                        SpH(4.gdp)
                    }
                }
            }
        )
    }

    with(imageUploadState.imagePicker) {
        if (this) {
            ImagePicker { file ->
                if (file != null) imageUploadStore.initImageFile(file)
                imageUploadStore.dismissImagePicker()
            }
        }
    }

    with(imageUploadState.imageViewer) {
        if (this) {
            ImageViewer(
                imagePath = imageUploadState.imageFile?.getPath() ?: "",
                dismiss = { imageUploadStore.dismissImageViewer() }
            )
        }
    }
}