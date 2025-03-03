package com.project.giunne.common.presentation.common.player.video.frame

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import com.project.giunne.common.presentation.common.player.video.source.DefaultControls
import com.project.giunne.common.presentation.common.player.video.source.MidControls
import com.project.giunne.common.presentation.common.player.video.source.PlayerController
import com.project.giunne.common.presentation.common.player.video.source.TopControls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun FramePlayer(
    modifier: Modifier = Modifier,
    url: String,
    size: IntSize,
    bytes: ByteArray?,
    controller: PlayerController,
    onFullScreenClicked: () -> Unit,
    isFullScreen: Boolean,
    isIntro: Boolean
) {
    val interaction = remember { MutableInteractionSource() }
    val isHovered by interaction.collectIsHoveredAsState()

    DisposableEffect(url) {
        controller.load(url)
        if (isIntro) controller.play()
        onDispose { controller.dispose() }
    }

    Box(
        modifier
            .wrapContentHeight()
            .hoverable(interaction),
    ) {
        FrameContainer(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            size = size,
            bytes = bytes,
            controller = controller,
            isIntro = isIntro
        )
        if (!isIntro) {
            TopControls(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
                controller = controller,
                isHovered = isHovered,
                onFullScreenClicked = onFullScreenClicked,
                isFullScreen = isFullScreen
            )
            MidControls(
                modifier = Modifier
                    .align(Alignment.Center),
                controller = controller,
                isHovered = isHovered,
            )
            DefaultControls(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                controller = controller,
                isHovered = isHovered
            )
        }
    }
}