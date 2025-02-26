package com.project.giunne.common.presentation.common.player

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.ZoomStore
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

    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .noRippleClickable {
                    dismiss()
                }
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val state = rememberZoomState(zoomStore, constraints)
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
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
}