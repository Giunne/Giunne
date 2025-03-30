package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.teacher.TeacherRoadmapComponent
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_attach_money
import com.project.giunne.icon_exp
import com.project.giunne.icon_stage

@Composable
fun RoadMapDialogEditable(
    roadMapComponent: TeacherRoadmapComponent,
    courseId: Long,
    roadMapState:  RoadMapState,
    questInfo: QuestInfo,
    nextQuestList: List<String>,
    onDismissDialog: () -> Unit = {},
) {
    val uriHandler = LocalUriHandler.current

    var youtubeUrl by remember { mutableStateOf(questInfo.guideUrl) }
    var questDescription by remember { mutableStateOf(questInfo.questDescription) }
    var stepDescription by remember { mutableStateOf(questInfo.trainingDescription) }
    var rewardCoin by remember { mutableStateOf(questInfo.rewardPoint.toString()) }
    var rewardExp by remember { mutableStateOf(questInfo.rewardExp.toString()) }

    if (roadMapState.modifySuccess) {
        GPAlertDialog(
            modifier = Modifier.padding(horizontal = 16.gdp),
            title = "로드맵 수정",
            content = "수정되었습니다!",
            dismiss = {
                roadMapComponent.dismissModifySuccessDialog()
            }
        )
    }

    roadMapState.error?.let { error ->
        GPAlertDialog(
            modifier = Modifier.padding(horizontal = 16.gdp),
            title = "로드맵 수정",
            content = error.message?.split(",")?.joinToString("\n") { it.split("]").last() }.orEmpty(),
            dismiss = {
                roadMapComponent.dismissErrorDialog()
            }
        )
    }
    Dialog(
        onDismissRequest = onDismissDialog,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .imePadding()
                .padding(16.gdp)
                .verticalScroll(rememberScrollState()),
        ) {
            ExerciseHeaderLink(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                title = questInfo.questName,
                openYoutubeLink = {
                    if (youtubeUrl.startsWith("https")) {
                        uriHandler.openUri(youtubeUrl )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.gdp))

            GPText(
                text = "운동 방법 페이지",
                textSize = 12.gsp
            )

            Spacer(modifier = Modifier.height(4.gdp))

            BorderContentField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = youtubeUrl,
                    onValueChange = {
                        youtubeUrl = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))

            GPText(
                text = "운동 설명",
                textSize = 12.gsp
            )

            Spacer(modifier = Modifier.height(4.gdp))

            BorderContentField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(GPColor.Transparent),
                    value = questDescription,
                    onValueChange = {
                        questDescription = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))

            GPText(
                text = "운동 방법",
                textSize = 12.gsp
            )

            Spacer(modifier = Modifier.height(4.gdp))

            BorderContentField(
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(GPColor.Transparent),
                    value = stepDescription,
                    onValueChange = {
                        stepDescription = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))

            BorderContentField(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(8.gdp))

                GPText(
                    text = "완료 보상",
                    textSize = 12.gsp
                )

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "경험치",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_exp
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GPText(
                            text = "+",
                            textSize = 12.gsp,
                            textColor = GPColor.MainOrangeColor
                        )
                        Spacer(modifier = Modifier.width(2.gdp))
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(GPColor.Transparent),
                            textStyle = TextStyle(
                                fontSize = 12.gsp,
                                color = GPColor.MainOrangeColor
                            ),
                            value = rewardExp,
                            onValueChange = { exp ->
                                if (exp.length <= 8 && exp.all { it.isDigit() }) {
                                    rewardExp = exp
                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "코인",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_attach_money
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GPText(
                            text = "+",
                            textSize = 12.gsp,
                            textColor = GPColor.MainOrangeColor
                        )
                        Spacer(modifier = Modifier.width(2.gdp))
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(GPColor.Transparent),
                            value = rewardCoin,
                            textStyle = TextStyle(
                                fontSize = 12.gsp,
                                color = GPColor.MainOrangeColor
                            ),
                            onValueChange = { coin ->
                                if (coin.length <= 8 && coin.all { it.isDigit() }) {
                                    rewardCoin = coin
                                }
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.gdp))

                if (nextQuestList.isNotEmpty()) {
                    ExerciseRewardSection(
                        title = nextQuestList.joinToString("\n") { it },
                        titleColor = GPColor.MainOrangeColor,
                        icon = Res.drawable.icon_stage
                    ) {
                        GPText(
                            text = "도전 가능!",
                            textSize = 12.gsp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.gdp))

            ExerciseActionButtons(
                onClose = onDismissDialog,
                isEditable = true,
                onConfirm = {
                    roadMapComponent.modifyQuestInfo(
                        courseId = courseId,
                        id = questInfo.id,
                        questDescription = questDescription,
                        trainingDescription = stepDescription,
                        rewardPoint = rewardCoin.toLong(),
                        rewardExp = rewardExp.toLong(),
                        guideUrl = youtubeUrl
                    )
                }
            )
        }
    }
}