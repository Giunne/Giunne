package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable
import java.io.File

@Composable
expect fun VideoPicker(
    callback: (PlatformFile?) -> Unit
)