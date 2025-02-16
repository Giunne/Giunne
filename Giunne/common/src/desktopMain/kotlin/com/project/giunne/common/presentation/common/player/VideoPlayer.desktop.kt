package com.project.giunne.common.presentation.common.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.project.giunne.common.util.gdp
import javafx.embed.swing.JFXPanel
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.media.MediaView
import javax.swing.JPanel

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    dismiss: () -> Unit,
    videoPath: String,
) {
    var jfxPanel by remember { mutableStateOf<JFXPanel?>(null) }
//    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    val frameController = remember(videoPath) { JfxController() }
    val width = 700.gdp
    val height = 500.gdp

    LaunchedEffect(Unit) {
//        Platform.runLater {
        println("1")
        val panel = JFXPanel()
        println("2 : $panel")
//            val media = Media(videoPath) // 파일 경로 설정
//            mediaPlayer = MediaPlayer(media)
        frameController.load("file://$videoPath")
//        frameController.load("file:///Users/heeung/Desktop/기운내 이미지/giwoonnae_intro.mp4")
//        frameController.load("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        println("3: ${frameController.player}")
//            val mediaView = MediaView(mediaPlayer)
        val mediaView = MediaView(frameController.player)

        println("4")

        mediaView.fitWidth = width.value.toDouble()
        mediaView.fitHeight = height.value.toDouble()
        mediaView.isPreserveRatio = true

        panel.scene = Scene(Group(mediaView), width.value.toDouble(), height.value.toDouble())
        frameController.player?.play()
//        mediaPlayer?.play()

        jfxPanel = panel
//        }
    }

    DisposableEffect(videoPath) {
        onDispose { frameController.dispose() }
    }

    if (jfxPanel != null) {
        Window(
            onCloseRequest = {
                jfxPanel = null
                dismiss()
            },
            title = "VideoPlayer",
            state = rememberWindowState(
                width = width,
                height = height + 200.gdp,
                position = WindowPosition(Alignment.Center),
                isMinimized = false,
            ),
            resizable = true,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                SwingPanel(
                    factory = {
                        JPanel().apply { add(jfxPanel) }
                    },
                    modifier = Modifier.width(width).height(height)
                )
                DefaultControls(
                    modifier = Modifier.fillMaxWidth(),
                    controller = frameController
                )
            }
        }
    }
}