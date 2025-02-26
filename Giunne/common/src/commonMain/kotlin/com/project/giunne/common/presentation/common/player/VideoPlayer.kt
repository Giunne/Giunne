package com.project.giunne.common.presentation.common.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoWindowPlayer(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    videoPath: String,
)

@Composable
expect fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoPath: String,
    onFullScreenClicked: () -> Unit
)