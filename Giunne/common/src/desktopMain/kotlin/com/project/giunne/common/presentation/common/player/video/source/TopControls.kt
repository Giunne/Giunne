package com.project.giunne.common.presentation.common.player.video.source

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import com.project.giunne.icon_fullscreen
import com.project.giunne.icon_pause
import com.project.giunne.icon_play
import com.project.giunne.icon_roadmap
import com.project.giunne.icon_rotate
import com.project.giunne.icon_sound
import com.project.giunne.icon_sound_mute
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToLong

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopControls(
    modifier: Modifier = Modifier,
    controller: PlayerController,
    isHovered: Boolean = false,
    onFullScreenClicked: () -> Unit,
    isFullScreen: Boolean
) {
    val state by controller.state.collectAsState()

    val animatedAlpha by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0f
    )

    val interaction = remember { MutableInteractionSource() }
    val soundHovered by interaction.collectIsHoveredAsState()

    val animatedDpState by animateDpAsState(
        targetValue = if (soundHovered) 80.gdp else 0.gdp
    )

    if (state.duration >= 100) {
        Row(
            modifier = modifier
                .alpha(animatedAlpha)
                .fillMaxWidth()
                .padding(vertical = 4.gdp, horizontal = 8.gdp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .hoverable(interaction)
                    .background(
                        color = GPColor.BackgroundLoading,
                        shape = RoundedCornerShape(12.gdp)
                    )
                    .padding(4.gdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPIconButton(
                    modifier = Modifier
                        .width(24.gdp)
                        .aspectRatio(1f),
                    normalColor = GPColor.Transparent,
                    pressColor = GPColor.Transparent,
                    shape = CircleShape,
                    onClick = { controller.toggleSound() },
                    icon = {
                        Image(
                            modifier = Modifier.size(12.gdp),
                            painter = if (state.isMuted || state.volume == 0f) painterResource(Res.drawable.icon_sound_mute)
                                else painterResource(Res.drawable.icon_sound),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(GPColor.White)
                        )
                    },
                    shadow = false
                )
                if (animatedDpState > 4.gdp) {
                    Slider(
                        modifier = Modifier
                            .height(24.gdp)
                            .width(animatedDpState),
                        value = state.volume,
                        onValueChange = { controller.setVolume(it) },
                        valueRange = 0f..1f,
                        thumb = {
                            Box(
                                modifier = Modifier
                                    .size(11.gdp)
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
                                        .height(3.gdp)
                                        .background(GPColor.White, CircleShape)
                                )
                                Box(
                                    Modifier
                                        .fillMaxWidth(1f - fraction)
                                        .align(Alignment.CenterEnd)
                                        .height(1.dp)
                                        .background(GPColor.White, CircleShape)
                                )
                            }
                        }
                    )
                }
            }
            if (!isFullScreen) {
                Box(
                    modifier = Modifier
                        .background(
                            color = GPColor.BackgroundLoading,
                            shape = RoundedCornerShape(12.gdp)
                        )
                        .padding(4.gdp),
                    contentAlignment = Alignment.Center
                ) {
                    GPIconButton(
                        modifier = Modifier
                            .width(24.gdp)
                            .aspectRatio(1f),
                        normalColor = GPColor.Transparent,
                        pressColor = GPColor.Transparent,
                        shape = CircleShape,
                        onClick = { onFullScreenClicked() },
                        icon = {
                            Image(
                                modifier = Modifier.size(12.gdp),
                                painter = painterResource(Res.drawable.icon_fullscreen),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(GPColor.White)
                            )
                        },
                        shadow = false
                    )
                }
            }
        }
    }
}