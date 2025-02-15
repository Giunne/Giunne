package com.project.giunne.common.presentation.common.toggle

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GPToggleButton(
    modifier: Modifier,
    isSelected: Boolean,
    onExerciseClick: () -> Unit,
    onJoggingClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = GPColor.ButtonLightGray,
                shape = RoundedCornerShape(12.gdp)
            )
    ) {
        val offset by animateOffsetAsState(
            targetValue = if (isSelected) Offset((maxWidth / 2).value, 0f) else Offset(0f, 0f),
            animationSpec = tween(durationMillis = 200, easing = LinearEasing)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .offset(offset.x.dp, offset.y.dp)
                .background(
                    color = GPColor.MainOrangeColor,
                    shape = if (isSelected) {
                        RoundedCornerShape(topEnd = 12.gdp, bottomEnd = 12.gdp)
                    } else {
                        RoundedCornerShape(topStart = 12.gdp, bottomStart = 12.gdp)
                    }
                )
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .noRippleClickable { onExerciseClick() },
                contentAlignment = Alignment.Center
            ) {
                GPText(
                    text = "운동",
                    textSize = 12.gsp,
                    textColor = GPColor.White,
                    fontFamily = GPFontFamily.Bold
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .noRippleClickable { onJoggingClick() },
                contentAlignment = Alignment.Center
            ) {
                GPText(
                    text = "조깅",
                    textSize = 12.gsp,
                    textColor = GPColor.White,
                    fontFamily = GPFontFamily.Bold
                )
            }
        }
    }
}