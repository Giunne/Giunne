package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

expect class PlatformFile {
    fun getPath(): String?
}