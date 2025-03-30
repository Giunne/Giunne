package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.project.giunne.common.data.remote.response.CourseInfo
import com.project.giunne.common.presentation.roadmap.dummy.joggingUiState
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun DrawTeacherJoggingLine(
    modifier: Modifier,
    weeks: List<String>,
    boxSize: Float,
    spacing: Float
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width

            val startX = (canvasWidth - (3 * boxSize + 2 * spacing)) / 2
            val startY = 0f

            weeks.indices.forEach { index ->
                val colIndex = index % 3
                val rowIndex = index / 3

                // 주차별 박스의 중앙값 계산
                val left = startX + colIndex * (boxSize + spacing)
                val top = startY + rowIndex * (boxSize + spacing)
                val centerX = left + boxSize / 2
                val centerY = top + boxSize / 2

                // 가로의 마지막 원소 전까지만 그려줌
                if (colIndex < 2) {
                    // 다음 박스의 중심점
                    val rightBoxLeft = startX + (colIndex + 1) * (boxSize + spacing)
                    val rightCenterX = rightBoxLeft + boxSize / 2

                    drawLine(
                        color = GPColor.BorderLightGray,
                        start = Offset(centerX, centerY),
                        end = Offset(rightCenterX, centerY),
                        strokeWidth = boxSize * 0.2f
                    )
                }
                /**
                 * 아래 박스와 연결
                 * 1. 왼쪽과 오른쪽 번갈아 가며 하나씩 연결 해야함
                 * 2. ex) 12-13, 10-9, 7-6 ...
                 */
                if (rowIndex % 2 == 0 && colIndex == 0 && index != 12) {
                    // 아래 박스와 연결
                    val bottomBoxTop = startY + (rowIndex + 1) * (boxSize + spacing)
                    val bottomCenterY = bottomBoxTop + boxSize / 2

                    drawLine(
                        color = GPColor.BorderLightGray,
                        start = Offset(centerX, centerY),
                        end = Offset(centerX, bottomCenterY),
                        strokeWidth = boxSize * 0.2f
                    )
                }

                if (rowIndex % 2 == 1 && colIndex == 2) {
                    // 아래 박스와 연결
                    val bottomBoxTop = startY + (rowIndex + 1) * (boxSize + spacing)
                    val bottomCenterY = bottomBoxTop + boxSize / 2
                    drawLine(
                        color = GPColor.BorderLightGray,
                        start = Offset(centerX, centerY),
                        end = Offset(centerX, bottomCenterY),
                        strokeWidth = boxSize * 0.2f
                    )
                }
            }
        }
    }
}