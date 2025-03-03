package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.state.ExerciseUiState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_attach_money
import com.project.giunne.icon_exp
import com.project.giunne.icon_stage

@Composable
fun RoadMapDialog(
    exerciseUiState: ExerciseUiState = ExerciseUiState(),
    onDismissDialog: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    Dialog(
        onDismissRequest = onDismissDialog
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.gdp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExerciseHeaderLink(
                title = exerciseUiState.title,
                openYoutubeLink = {
                    uriHandler.openUri(exerciseUiState.youtubeUrl)
                }
            )

            Spacer(modifier = Modifier.height(16.gdp))

            ExerciseDescription(exerciseUiState.description)

            Spacer(modifier = Modifier.height(16.gdp))

            BorderContentField(
                modifier = Modifier.fillMaxWidth()
            ) {
                ExerciseStep(exerciseUiState.exerciseStep)
            }

            Spacer(modifier = Modifier.height(16.gdp))

            BorderContentField(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(8.gdp))

                GPText(
                    text = "완료 보상",
                    textSize = 14.gsp
                )

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "경험치",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_exp
                ) {
                    GPText(
                        text = exerciseUiState.rewardExp.toString(),
                        textSize = 12.gsp,
                        textColor = GPColor.MainOrangeColor
                    )
                }

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "코인",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_attach_money
                ) {
                    GPText(
                        text = "+${exerciseUiState.rewardCoin}",
                        textSize = 12.gsp,
                        textColor = GPColor.MainOrangeColor
                    )
                }
                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = exerciseUiState.nextExerciseTitle,
                    titleColor = GPColor.MainOrangeColor,
                    icon = Res.drawable.icon_stage
                ) {
                    GPText(
                        text = "도전 가능!",
                        textSize = 12.gsp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.gdp))

            ExerciseActionButtons(
                onClose = onDismissDialog,
                onConfirm = {
                    onConfirm()
                }
            )
        }
    }
}
