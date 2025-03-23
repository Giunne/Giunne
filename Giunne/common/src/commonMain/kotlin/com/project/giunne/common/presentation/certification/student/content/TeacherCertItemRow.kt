package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.convertType
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.serialization.json.JsonNull.content

@Composable
fun TeacherCertItemRow(
    questUploadInfo: QuestUploadInfo,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {  }
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed -> GPColor.ButtonPressWhite
            isHovered -> GPColor.ButtonHoverWhite
            else -> GPColor.White
        },
    )

    Box {
        Row(
            modifier = Modifier
                .padding(vertical = 4.gdp)
                .fillMaxWidth()
                .height(68.gdp)
                .background(
                    color = fillColor,
                    shape = RoundedCornerShape(12.gdp)
                )
                .padding(horizontal = 8.gdp)
                .noRippleClickable(interactionSource = interactionSource) { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            GPSquircleShape(
                modifier = Modifier.size(52.gdp),
                backgroundColor = GPColor.BackgroundLightGray,
                content = {
                    GPSmallCharacter(
                        modifier = Modifier.fillMaxSize(),
                        wearingItems = questUploadInfo.playerInfo.wearingItems
                    )
                }
            )
            SpW(8.gdp)
            GPText(
                text = questUploadInfo.playerInfo.nickname,
                textColor = GPColor.TextBlack_232323,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Regular
            )
            SpW(16.gdp)
            GPText(
                text = "${questUploadInfo.trainingType.convertType()} ",
                textColor = GPColor.MainOrangeColor,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold
            )
            GPText(
                modifier = Modifier.weight(1f),
                text = questUploadInfo.questName.replace(".", "단계 "),
                textColor = GPColor.TextBlack,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold
            )
        }
//        Row(
//            modifier = Modifier
//                .padding(8.gdp)
//                .align(Alignment.TopEnd),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            GPText(
//                text = "24.11.11", // TODO API
//                textColor = GPColor.TextLightGray,
//                fontFamily = GPFontFamily.Bold,
//                textSize = 10.gsp
//            )
//        }
    }
}
