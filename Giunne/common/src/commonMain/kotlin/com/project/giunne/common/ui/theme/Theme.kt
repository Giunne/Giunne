package com.project.giunne.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

//@OptIn(ExperimentalCoilApi::class)
@Composable
fun GiunnaeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = GPColor.MainOrangeColor,
            background = GPColor.White
        )
    ) {
        content()
    }
}