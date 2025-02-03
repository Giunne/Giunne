package com.project.giunne.common.presentation.common.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import coil3.compose.AsyncImage
import com.project.giunne.common.util.gdp

@Composable
actual fun ImageViewer(
    modifier: Modifier,
    dismiss: () -> Unit,
    imagePath: String
) {
    Window(
        onCloseRequest = {
            dismiss()
        },
        title = "VideoPlayer",
        state = rememberWindowState(
            width = 600.gdp,
            height = 800.gdp,
            position = WindowPosition(Alignment.Center),
            isMinimized = false,
        ),
        resizable = true,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.gdp)),
            model = imagePath,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}