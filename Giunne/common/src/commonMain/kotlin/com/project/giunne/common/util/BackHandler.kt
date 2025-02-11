package com.project.giunne.common.util

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(
    enabled: Boolean = true, onBack: () -> Unit
)

expect fun exitProgram()