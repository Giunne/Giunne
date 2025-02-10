package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.io.File

@Composable
expect fun ImagePicker(
    callback: (PlatformFile?) -> Unit
)