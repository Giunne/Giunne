package com.project.giunne.common.presentation.common.player

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import coil3.compose.AsyncImage
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.ZoomStore
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.onZoomEvent
import com.project.giunne.common.util.rememberZoomState
import com.project.giunne.common.util.removeSpaceUrl
import com.project.giunne.icon_rotate
import com.project.giunne.icon_search
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun ImageViewer(
    modifier: Modifier,
    dismiss: () -> Unit,
    imagePath: String
) {
    val scope = rememberCoroutineScope()
    val zoomStore = remember { ZoomStore() }
    val zoomUiState by zoomStore.uiState.collectAsState()

    var rotate by remember { mutableStateOf(0f) }
    val animateRotate by animateFloatAsState(targetValue = rotate)

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
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                            translationY = zoomUiState.offsetY,
                            rotationZ = animateRotate
                        )
                        .transformable(state)
                        .onZoomEvent(
                            scope = scope,
                            state = state,
                            onSingleTapEvent = {

                            }
                        ),
                    model = imagePath.removeSpaceUrl(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }

            GPIconButton(
                modifier = Modifier
                    .padding(10.gdp)
                    .size(30.gdp)
                    .align(Alignment.BottomEnd),
                icon = {
                    Image(
                        modifier = Modifier.size(20.gdp),
                        painter = painterResource(Res.drawable.icon_rotate),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.White)
                    )
                },
                normalColor = GPColor.ButtonBlack,
                pressColor = GPColor.ButtonPressBlack,
                hoverColor = GPColor.ButtonHoverBlack,
                onClick = {
                    rotate += 90f
                },
                shadow = false
            )
        }
    }
}