package com.project.giunne.common.presentation.common.progress

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun GPCertCircleProgress(
    modifier: Modifier = Modifier,
    percent: Float,
) {
    var progress by remember { mutableFloatStateOf(0f) }
    val size by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        ),
        label = "levelProgressAnimation"
    )

    LaunchedEffect(Unit) {
        progress = percent // 현재 Level Percent
    }

    CircularProgressIndicator(
        modifier = modifier,
        progress = { size },
        color = GPColor.MainOrangeColor,
        strokeWidth = 12.gdp,
        trackColor = GPColor.BackgroundGray_F6F6F6,
    )
}