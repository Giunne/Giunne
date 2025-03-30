package com.project.giunne.common.presentation.roadmap.student

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.presentation.roadmap.content.RoadMapDialog
import com.project.giunne.common.presentation.roadmap.content.RoadMapExerciseStage
import com.project.giunne.common.presentation.roadmap.node.roadMap1
import com.project.giunne.common.presentation.roadmap.node.roadMap2
import com.project.giunne.common.presentation.roadmap.node.roadMap3
import com.project.giunne.common.presentation.roadmap.node.roadMap4
import com.project.giunne.common.presentation.roadmap.node.roadMap5
import com.project.giunne.common.presentation.roadmap.student.state.StudentRoadMapState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ExerciseRoadmapScreen(
    modifier: Modifier = Modifier,
    roadMapState: StudentRoadMapState,
    navigateToCertification: () -> Unit,
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var isShow by remember { mutableStateOf(false) }
    var courseInfo by remember { mutableStateOf(StudentCourseInfo()) }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {

        if (isShow) {
            RoadMapDialog(
                onDismissDialog = {
                    isShow = false
                },
                questInfo = courseInfo.questInfo,
                nextQuestList = roadMapState.courseMap[courseInfo.id.toLong()]
                    ?.map { it.title }
                    ?.distinct()
                    ?: listOf(),
                onConfirm = {
                    isShow = false
                    navigateToCertification()
                }
            )
        }

        val width = maxWidth.value
        val height = maxHeight.value

        val (node1, connect1) = roadMap1(width, height)
        val (node2, connect2) = roadMap2(width, height)
        val (node3, connect3) = roadMap3(width, height)
        val (node4, connect4) = roadMap4(width, height)
        val (node5, connect5) = roadMap5(width, height)

        offsetY = offsetY.coerceIn(0f, height * 2)
        offsetX = if (offsetY < height / 2) {
            offsetX.coerceIn(-offsetY, 0f)
        } else {
            offsetX.coerceIn(-width, 0f)
        }

        val text = if (offsetY in 0f..height / 2) {
            "모두 협력해 보아요."
        } else if (offsetY in height / 2..height && offsetX in -width..0f) {
            "3명이 한팀을 이루어야 해요!"
        } else {
            "혼자 해내야 해요!"
        }

        RoadMapExerciseStage(
            offset = Offset(offsetX, offsetY),
            questInfoList = roadMapState.courseMap.values.flatten(),
            node = node1,
            connect = connect1,
            onExerciseClicked = {
                courseInfo = it
                isShow = true
            }
        )

        RoadMapExerciseStage(
            offset = Offset(offsetX, offsetY - height),
            node = node2,
            questInfoList = roadMapState.courseMap.values.flatten(),
            connect = connect2,
            onExerciseClicked = {
                courseInfo = it
                isShow = true
            }
        )

        RoadMapExerciseStage(
            offset = Offset(offsetX, offsetY - height * 2),
            node = node3,
            questInfoList = roadMapState.courseMap.values.flatten(),
            connect = connect3,
            onExerciseClicked = {
                courseInfo = it
                isShow = true
            }
        )

        RoadMapExerciseStage(
            offset = Offset(offsetX + width, offsetY - height),
            node = node4,
            questInfoList = roadMapState.courseMap.values.flatten(),
            connect = connect4,
            onExerciseClicked = {
                courseInfo = it
                isShow = true
            }
        )

        RoadMapExerciseStage(
            offset = Offset(offsetX + width, offsetY - height * 2),
            node = node5,
            questInfoList = roadMapState.courseMap.values.flatten(),
            connect = connect5,
            onExerciseClicked = {
                courseInfo = it
                isShow = true
            }
        )

        GPAnnotatedText(
            modifier = Modifier
                .padding(16.gdp)
                .align(Alignment.TopStart),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = GPColor.MainOrangeColor,
                        fontSize = 14.gsp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(text.substring(0, 2))
                }
                append(text.substring(2))
            },
            textSize = 12.gsp,
            fontFamily = GPFontFamily.Bold
        )
    }
}