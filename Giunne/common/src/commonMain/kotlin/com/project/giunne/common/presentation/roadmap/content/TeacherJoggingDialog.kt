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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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

@Composable
fun TeacherJoggingDialog(
    roadMapComponent: TeacherRoadmapComponent,
    roadMapState: RoadMapState,
    questInfo: QuestInfo,
    onDismissDialog: () -> Unit = {},
) {

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
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GPText(
                text = questInfo.questName,
                textSize = 18.gsp
            )

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
                            onValueChange = {
                                rewardExp = it
                            }
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
                            onValueChange = {
                                rewardCoin = it
                            }
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
                        id = questInfo.id,
                        rewardPoint = rewardCoin.toLong(),
                        rewardExp = rewardExp.toLong(),
                    )
                }
            )
        }
    }
}