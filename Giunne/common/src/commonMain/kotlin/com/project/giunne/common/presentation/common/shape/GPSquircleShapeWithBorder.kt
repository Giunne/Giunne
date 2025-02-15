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

@Composable
fun GPSquircleShapeWithBorder(
    modifier: Modifier,
    backgroundColor: Color,
    borderColor: Color,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) { // 원하는 크기
            val width = size.width
            val height = size.height

            val path = Path().apply {
                // 시작점 (상단 중앙)
                moveTo(width / 2, 0f)

                // 상단 오른쪽 코너
                cubicTo(
                    width.times(0.95f), 0f, // 첫 번째 컨트롤 포인트
                    width, height.times(0.05f), // 두 번째 컨트롤 포인트
                    width, height / 2 // 도착점
                )

                // 오른쪽 아래 코너
                cubicTo(
                    width, height.times(0.95f), // 첫 번째 컨트롤 포인트
                    width.times(0.95f), height, // 두 번째 컨트롤 포인트
                    width / 2, height // 도착점
                )

                // 아래쪽 왼쪽 코너
                cubicTo(
                    width.times(0.05f), height, // 첫 번째 컨트롤 포인트
                    0f, height.times(0.95f), // 두 번째 컨트롤 포인트
                    0f, height / 2 // 도착점
                )

                // 왼쪽 위 코너
                cubicTo(
                    0f, height.times(0.05f),
                    width.times(0.05f), 0f,
                    width / 2, 0f
                )

                close()
            }

            drawPath(
                path = path,
                color = backgroundColor
            )

            drawPath(
                path = path,
                color = borderColor,
                style = Stroke(width / 18)
            )
        }
        content()
    }
}