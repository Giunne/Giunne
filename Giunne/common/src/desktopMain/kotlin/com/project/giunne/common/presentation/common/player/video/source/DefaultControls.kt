package com.project.giunne.common.presentation.common.player.video.source

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_pause
import com.project.giunne.icon_play
import com.project.giunne.icon_roadmap
import com.project.giunne.icon_rotate
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToLong

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultControls(
    modifier: Modifier = Modifier,
    controller: PlayerController,
    isHovered: Boolean = false
) {
    val state by controller.state.collectAsState()

    val animatedTimestamp by animateFloatAsState(state.timestamp.toFloat())
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0f
    )

    if (state.duration >= 100) {
        Row(
            modifier = modifier
                .alpha(animatedAlpha)
                .padding(4.gdp)
                .background(
                    color = GPColor.BackgroundLoading,
                    shape = RoundedCornerShape(12.gdp)
                )
                .fillMaxWidth()
                .padding(horizontal = 12.gdp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.isPlaying) {
                GPIconButton(
                    modifier = Modifier
                        .width(24.gdp)
                        .aspectRatio(1f),
                    normalColor = GPColor.ButtonBlack,
                    pressColor = GPColor.ButtonPressBlack,
                    shape = CircleShape,
                    onClick = controller::pause,
                    icon = {
                        Image(
                            modifier = Modifier.size(12.gdp),
                            painter = painterResource(Res.drawable.icon_pause),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(GPColor.White)
                        )
                    }
                )
            } else {
                GPIconButton(
                    modifier = Modifier
                        .width(24.gdp)
                        .aspectRatio(1f),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    shape = CircleShape,
                    onClick = controller::play,
                    icon = {
                        Image(
                            modifier = Modifier.size(12.gdp),
                            painter = painterResource(Res.drawable.icon_play),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(GPColor.White)
                        )
                    }
                )
            }
            Slider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.gdp),
                value = animatedTimestamp,
                onValueChange = { controller.seekTo(it.roundToLong()) },
                valueRange = 0f..state.duration.toFloat(),
                thumb = {
                    Box(
                        modifier = Modifier
                            .size(16.gdp)
                            .background(
                                color = GPColor.White,
                                shape = CircleShape
                            )
                    )
                },
                track = { sliderState ->
                    val fraction by remember {
                        derivedStateOf {
                            (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction)
                                .align(Alignment.CenterStart)
                                .height(6.gdp)
                                .padding(end = 6.gdp)
                                .background(GPColor.BackgroundFrameOrange, CircleShape)
                        )
                        Box(
                            Modifier
                                .fillMaxWidth(1f - fraction)
                                .align(Alignment.CenterEnd)
                                .height(2.dp)
                                .padding(start = 6.dp)
                                .background(GPColor.White, CircleShape)
                        )
                    }
                }
            )
            GPText(
                text = state.timestamp.formatTimestamp(),
                textSize = 12.gsp,
                textColor = GPColor.BackgroundFrameOrange
            )
            SpW(8.gdp)
            GPIconButton(
                modifier = Modifier
                    .width(24.gdp)
                    .aspectRatio(1f),
                normalColor = GPColor.Transparent,
                pressColor = GPColor.Transparent,
                shape = CircleShape,
                onClick = { controller.rotate(state.rotate + 90f) },
                icon = {
                    Image(
                        modifier = Modifier.size(12.gdp),
                        painter = painterResource(Res.drawable.icon_rotate),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.White)
                    )
                }
            )
        }
    }
}