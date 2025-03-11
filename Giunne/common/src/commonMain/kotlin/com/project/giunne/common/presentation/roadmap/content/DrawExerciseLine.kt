package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.presentation.roadmap.node.Connect
import com.project.giunne.common.presentation.roadmap.node.ConnectNode
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun DrawExerciseLine(
    density: Float,
    questInfoList: List<StudentCourseInfo>,
    connect: List<ConnectNode>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            connect.forEach {
                val from = it.from
                val to = it.to
                val connection = it.connect
                val fromCourseInfo = questInfoList.find { it.courseName == from.step } ?: StudentCourseInfo()
                val toCourseInfo = questInfoList.find { it.courseName == to.step } ?: StudentCourseInfo()
                val fromQuestStateInfo = fromCourseInfo.questInfo.questStateInfo
                val toQuestStateInfo = toCourseInfo.questInfo.questStateInfo
                val fromStatus = fromQuestStateInfo.questProgress
                val toStatus = toQuestStateInfo.questProgress
                val defaultColor = if (from.boxSize == 30f) GPColor.MainOrangeColor else GPColor.ButtonLightGray
                val color = if (fromStatus == "CONFIRM") {
                    GPColor.Green
                } else if (toStatus == "LOCK_OPEN" && from.boxSize == 30f) { // 화면 그리위한 노드는 바로 연결 시켜줘야함
                    GPColor.Green
                } else {
                    defaultColor
                }
                when (connection) {
                    Connect.BOTTOM_CURVE, Connect.TOP_CURVE -> {
                        Path().apply {
                            moveTo(from.centerOffset.x * density, from.centerOffset.y * density)
                            quadraticTo(
                                x1 = it.controlPoint.x * density,
                                y1 = it.controlPoint.y * density,
                                x2 = to.centerOffset.x * density,
                                y2 = to.centerOffset.y * density
                            )
                        }.also { path ->
                            drawPath(
                                path = path,
                                color = color,
                                style = Stroke(width = from.boxSize * 0.2f * density)
                            )
                        }
                    }
                    else -> {
                        drawLine(
                            color = color,
                            start = Offset(from.centerOffset.x * density, from.centerOffset.y * density),
                            end = Offset(to.centerOffset.x * density, to.centerOffset.y * density),
                            strokeWidth = from.boxSize * 0.2f * density
                        )
                    }
                }
            }
        }
    }
}