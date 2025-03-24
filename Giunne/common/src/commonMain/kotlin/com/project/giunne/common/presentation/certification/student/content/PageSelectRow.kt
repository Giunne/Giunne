package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_roadmap
import com.project.giunne.icon_running
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun PageSelectRow(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    page: CertPage,
    onRoadmapClicked: () -> Unit,
    onRunningClicked: () -> Unit,
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val roadmapContentColor by animateColorAsState(
        targetValue = when (page) {
            CertPage.RoadMap -> GPColor.White
            CertPage.Running -> GPColor.ButtonLightGray
        },
    )

    val runningContentColor by animateColorAsState(
        targetValue = when (page) {
            CertPage.RoadMap -> GPColor.ButtonLightGray
            CertPage.Running -> GPColor.White
        },
    )

    val animatedFloat by animateFloatAsState(
        targetValue = when (page) {
            CertPage.RoadMap -> -1f
            CertPage.Running -> 1f
        }
    )

    Box(
        modifier = modifier,
    ){
        Box(
            modifier = Modifier
                .align(BiasAlignment(animatedFloat, 0f))
                .height(48.gdp)
                .fillMaxSize(0.5f)
//                .width(152.gdp)
                .background(
                    color = GPColor.MainOrangeColor,
                    shape = RoundedCornerShape(12.gdp)
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .height(48.gdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .noRippleClickable {
                        onRoadmapClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(20.gdp),
                        painter = painterResource(Res.drawable.icon_roadmap),
                        contentDescription = "roadmapIcon",
                        colorFilter = ColorFilter.tint(color = roadmapContentColor)
                    )
                    SpW(2.gdp)
                    GPText(
                        text = "로드맵 인증",
                        textColor = roadmapContentColor,
                        fontFamily = GPFontFamily.Bold,
                        textSize = 16.gsp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .noRippleClickable {
                        onRunningClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(20.gdp),
                        painter = painterResource(Res.drawable.icon_running),
                        contentDescription = "roadmapIcon",
                        colorFilter = ColorFilter.tint(color = runningContentColor)
                    )
                    SpW(2.gdp)
                    GPText(
                        text = "러닝 인증",
                        textColor = runningContentColor,
                        fontFamily = GPFontFamily.Bold,
                        textSize = 16.gsp
                    )
                }
            }
        }
    }
}