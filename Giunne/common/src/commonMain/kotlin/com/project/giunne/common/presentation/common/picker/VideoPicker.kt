package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable

@Composable
expect fun VideoPicker(
    callback: (PlatformFile?) -> Unit
)