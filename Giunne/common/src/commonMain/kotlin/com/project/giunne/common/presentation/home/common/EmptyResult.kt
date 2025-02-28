package com.project.giunne.common.presentation.home.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.empty.GPResultEmpty

@Composable
fun EmptyResult(
    modifier: Modifier,
    description: String,
    highlightRegex: IntRange
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        GPResultEmpty(
            title = description,
            highlightRange = highlightRegex
        )
    }
}