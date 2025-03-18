package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun RoadMapGradeCount(
    modifier: Modifier = Modifier,
    starPoint: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(starPoint) {
            val icon = Icons.Rounded.Star
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = icon,
                contentDescription = "등급",
                tint = GPColor.ARankItemColor1
            )
        }
    }
}