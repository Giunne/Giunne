package com.project.giunne.common.presentation.common.player.video.frame

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import com.project.giunne.common.presentation.common.noRippleClickableWithoutHover
import com.project.giunne.common.presentation.common.player.video.source.DefaultControls
import com.project.giunne.common.presentation.common.player.video.source.PlayerController
import com.project.giunne.common.presentation.common.player.video.source.TopControls
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FramePlayer(
    modifier: Modifier = Modifier,
    url: String,
    size: IntSize,
    bytes: ByteArray?,
    controller: PlayerController,
    onFullScreenClicked: () -> Unit,
    isFullScreen: Boolean
) {
    val interaction = remember { MutableInteractionSource() }
    val isHovered by interaction.collectIsHoveredAsState()

    DisposableEffect(url) {
        controller.load(url)
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
            controller = controller
        )
        TopControls(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            controller = controller,
            isHovered = isHovered,
            onFullScreenClicked = onFullScreenClicked,
            isFullScreen = isFullScreen
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