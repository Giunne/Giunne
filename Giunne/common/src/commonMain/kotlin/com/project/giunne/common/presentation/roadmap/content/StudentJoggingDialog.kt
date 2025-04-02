package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_attach_money
import com.project.giunne.icon_exp

@Composable
fun StudentJoggingDialog(
    questInfo: StudentQuestInfo,
    onDismissDialog: () -> Unit = {  },
    onConfirm: () -> Unit
) {
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
                    textSize = 14.gsp
                )

                Spacer(modifier = Modifier.height(8.gdp))

                ExerciseRewardSection(
                    title = "경험치",
                    titleColor = GPColor.TextBlack,
                    icon = Res.drawable.icon_exp
                ) {
                    GPText(
                        text = questInfo.rewardExp.toString(),
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
                        text = "+${questInfo.rewardPoint}",
                        textSize = 12.gsp,
                        textColor = GPColor.MainOrangeColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.gdp))

            StudentJoggingActionButtons(
                onClose = onDismissDialog,
                progressState = questInfo.questStateInfo.questProgress,
                onConfirm = when (questInfo.questStateInfo.questProgress) {
                    "CHECK" -> { { onConfirm() } }
                    else -> null
                }
            )
        }
    }
}