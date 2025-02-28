package com.project.giunne.common.presentation.common.player.video.source

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import com.project.giunne.common.presentation.common.player.video.frame.FramePlayer
import java.awt.Component

@Composable
fun PlayerSource(
    url: String,
    component: Component,
    componentController: PlayerController,
    size: IntSize,
    bytes: ByteArray?,
    frameController: PlayerController,
    onFullScreenClicked: () -> Unit,
    isFullScreen: Boolean = false,
    isIntro: Boolean = false
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FramePlayer(
            modifier = Modifier.weight(1f),
            url = url,
            size = size,
            bytes = bytes,
            controller = frameController,
            onFullScreenClicked = onFullScreenClicked,
            isFullScreen = isFullScreen,
            isIntro = isIntro
        )
    }
}