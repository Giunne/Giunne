package com.project.giunne.common.presentation.common.player

import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import coil3.compose.AsyncImage
import com.project.giunne.common.util.ZoomStore
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.onZoomEvent
import com.project.giunne.common.util.rememberZoomState

@Composable
actual fun ImageViewer(
    modifier: Modifier,
    dismiss: () -> Unit,
    imagePath: String
) {
    val scope = rememberCoroutineScope()
    val zoomStore = remember { ZoomStore() }
    val zoomUiState by zoomStore.uiState.collectAsState()

    Window(
        onCloseRequest = {
            dismiss()
        },
        title = "이미지",
        state = rememberWindowState(
            width = 600.gdp,
            height = 800.gdp,
            position = WindowPosition(Alignment.Center),
            isMinimized = false,
        ),
        resizable = true,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = rememberZoomState(zoomStore, constraints)

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.gdp))
                    .graphicsLayer(
                        scaleX = zoomUiState.scale,
                        scaleY = zoomUiState.scale,
                        translationX = zoomUiState.offsetX,
                        translationY = zoomUiState.offsetY
                    )
                    .transformable(state)
                    .onZoomEvent(
                        scope = scope,
                        state = state,
                        onSingleTapEvent = {

                        }
                    ),
                model = imagePath,
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}