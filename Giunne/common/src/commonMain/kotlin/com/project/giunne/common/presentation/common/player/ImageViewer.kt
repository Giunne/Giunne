package com.project.giunne.common.presentation.common.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ImageViewer(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    imagePath: String,
)