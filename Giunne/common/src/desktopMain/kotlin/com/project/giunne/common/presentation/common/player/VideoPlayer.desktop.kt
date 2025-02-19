package com.project.giunne.common.presentation.common.player

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.project.giunne.common.presentation.common.player.video.javafx.JfxComponentController
import com.project.giunne.common.presentation.common.player.video.javafx.JfxFrameController
import com.project.giunne.common.presentation.common.player.video.source.PlayerSource
import com.project.giunne.common.util.gdp
import java.io.File
import java.net.URI

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    dismiss: () -> Unit,
    videoPath: String,
) {
//    val videoPath = "https://hu-sh.synology.me:10004/upload/20250218_231157.mp4"
    val componentController = remember(videoPath) { JfxComponentController() }
    val frameController = remember(videoPath) { JfxFrameController() }

    Window(
        onCloseRequest = {
            dismiss()
        },
        title = "VideoPlayer",
        state = rememberWindowState(
            width = 600.gdp,
            height = 900.gdp,
            position = WindowPosition(Alignment.Center),
            isMinimized = false,
        ),
        resizable = true,
    ) {
        PlayerSource(
            url = videoPath.run {
                (runCatching(URI::create).getOrNull() ?: File(this).toURI()).toString()
            },
            component = componentController.component,
            componentController = componentController,
            size = frameController.size.collectAsState(null).value?.run {
                IntSize(first, second)
            } ?: IntSize.Zero,
            bytes = frameController.bytes.collectAsState(null).value,
            frameController = frameController
        )
    }
}