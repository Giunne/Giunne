package com.project.giunne.common.presentation.common.player.video.frame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.project.giunne.common.presentation.common.player.video.source.PlayerController
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import org.jetbrains.skia.Bitmap

@Composable
fun FrameContainer(
    modifier: Modifier = Modifier,
    size: IntSize,
    bytes: ByteArray?,
    controller: PlayerController
) {
    var viewSize by remember { mutableStateOf(IntSize.Zero) }
    val ratio = size.width.toFloat() / size.height.toFloat()

    val state by controller.state.collectAsState()

    val bitmap by remember(size) {
        derivedStateOf {
            if (size.width > 0 && size.height > 0) Bitmap().apply {
                allocN32Pixels(size.width, size.height, true)
            }
            else null
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .onSizeChanged {
                viewSize = it
            },
        contentAlignment = Alignment.Center
    ) {
        bitmap?.let { bitmap ->
            bytes?.let { bytes ->
                Image(
                    modifier = Modifier
//                        .fillMaxHeight()
//                        .aspectRatio(ratio)
                        .align(Alignment.Center)
                        .rotate(state.rotate),
                    bitmap = bitmap.run {
                        installPixels(bytes)
                        asComposeImageBitmap()
                    },
                    contentDescription = "frame"
                )
            }
        } ?: CircularProgressIndicator(
            modifier = Modifier
                .width(40.gdp),
            color = GPColor.White,
            trackColor = GPColor.MainOrangeColor,
            strokeWidth = 6.gdp
        )
    }
}