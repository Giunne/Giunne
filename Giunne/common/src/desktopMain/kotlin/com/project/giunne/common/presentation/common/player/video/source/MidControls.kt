package com.project.giunne.common.presentation.common.player.video.source

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_pause
import com.project.giunne.icon_play
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MidControls(
    modifier: Modifier = Modifier,
    controller: PlayerController,
    isHovered: Boolean = false,
) {
    val state by controller.state.collectAsState()
    val animateButtonColor by animateColorAsState(
        targetValue = if (state.isPlaying) GPColor.ButtonBlack else GPColor.ButtonOrange
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isHovered) 1f else 0f
    )

    Box(
        modifier = modifier
    ) {
        GPIconButton(
            modifier = Modifier
                .alpha(animatedAlpha)
                .width(32.gdp)
                .aspectRatio(1f),
            normalColor = animateButtonColor,
            pressColor = if (state.isPlaying) GPColor.ButtonPressBlack else GPColor.ButtonPressOrange,
            shape = CircleShape,
            onClick = if (state.isPlaying) controller::pause else controller::play,
            icon = {
                if (state.isPlaying) {
                    Image(
                        modifier = Modifier.size(16.gdp),
                        painter = painterResource(Res.drawable.icon_pause),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.White)
                    )
                } else {
                    Image(
                        modifier = Modifier.size(16.gdp),
                        painter = painterResource(Res.drawable.icon_play),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.White)
                    )
                }
            },
            shadow = false
        )
    }
}