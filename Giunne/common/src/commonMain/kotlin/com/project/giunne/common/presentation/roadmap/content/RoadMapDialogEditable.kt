package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
fun RoadMapDialogEditable(
    exerciseUiState: ExerciseUiState = ExerciseUiState(),
    onUrlChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onStepChange: (String) -> Unit = {},
    onRewardExpChange: (String) -> Unit = {},
    onRewardCoinChange: (String) -> Unit = {},
    onDismissDialog: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

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

            ExerciseHeaderLink(
                title = exerciseUiState.title,
                openYoutubeLink = {
                    uriHandler.openUri(exerciseUiState.youtubeUrl)
                }
            )

            Spacer(modifier = Modifier.height(16.gdp))

            BorderContentField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = exerciseUiState.youtubeUrl,
                    onValueChange = {
                        onUrlChange(it)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))

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
                    value = exerciseUiState.description,
                    onValueChange = {
                        onDescriptionChange(it)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))

            BorderContentField(
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(GPColor.Transparent),
                    value = exerciseUiState.exerciseStep,
                    onValueChange = {
                        onStepChange(it)
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
                    textSize = 14.gsp
                )

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "경험치",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_exp
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(GPColor.Transparent),
                        textStyle = TextStyle(
                            fontSize = 12.gsp,
                            color = GPColor.MainOrangeColor
                        ),
                        value = exerciseUiState.rewardExp.toString(),
                        onValueChange = {
                            onRewardExpChange(it)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "코인",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_attach_money
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(GPColor.Transparent),
                        value = "+${exerciseUiState.rewardCoin}",
                        textStyle = TextStyle(
                            fontSize = 12.gsp,
                            color = GPColor.MainOrangeColor
                        ),
                        onValueChange = {
                            onRewardCoinChange(it)
                        }
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