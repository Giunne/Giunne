package com.project.giunne.common.presentation.common.shape

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun GPSquircleBorderShape(
    modifier: Modifier,
    backgroundColor: Color,
    borderColor: Color,
    borderWidth: Dp,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(width / 2, 0f)

                cubicTo(
                    width.times(0.95f), 0f,
                    width, height.times(0.05f),
                    width, height / 2
                )

                cubicTo(
                    width, height.times(0.95f),
                    width.times(0.95f), height,
                    width / 2, height
                )

                cubicTo(
                    width.times(0.05f), height,
                    0f, height.times(0.95f),
                    0f, height / 2
                )

                cubicTo(
                    0f, height.times(0.05f),
                    width.times(0.05f), 0f,
                    width / 2, 0f
                )

                close()
            }

            // 배경 그리기
            drawPath(
                path = path,
                color = backgroundColor
            )

            // 테두리 그리기
            drawPath(
                path = path,
                color = borderColor,
                style = Stroke(width = borderWidth.value)
            )
        }
        content()
    }
}