package com.project.giunne.common.presentation.common.player.video.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import com.project.giunne.common.presentation.common.player.video.source.DefaultControls
import com.project.giunne.common.presentation.common.player.video.source.PlayerController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FramePlayer(
    modifier: Modifier = Modifier,
    url: String,
    size: IntSize,
    bytes: ByteArray?,
    controller: PlayerController,
) {
    val scope = rememberCoroutineScope()

    DisposableEffect(url) {
        controller.load(url)
//        scope.launch {
//            delay(100)
//            controller.seekTo(80)
//        }
        onDispose { controller.dispose() }
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FrameContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            size = size,
            bytes = bytes
        )
        DefaultControls(
            modifier = Modifier
                .fillMaxWidth(),
            controller = controller
        )
    }
}