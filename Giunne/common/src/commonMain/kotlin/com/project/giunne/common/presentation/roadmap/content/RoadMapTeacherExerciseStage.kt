package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.CourseInfo
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShapeWithBorder
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.node.ConnectNode
import com.project.giunne.common.presentation.roadmap.node.Node
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun RoadMapTeacherExerciseStage(
    offset: Offset,
    questInfoList: List<CourseInfo>,
    node: List<Node>,
    connect: List<ConnectNode>,
    onExerciseClicked: (CourseInfo) -> Unit,
) {
    val density = LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(offset.x.dp, offset.y.dp),
    ) {
        DrawTeacherExerciseLine(
            density = density,
            connect = connect
        )
        node.forEach { node ->
            val courseInfo = questInfoList.find { it.courseName == node.step } ?: CourseInfo()
            val borderColor = if (node.boxSize == 30f) {
                GPColor.MainOrangeColor
            } else {
                GPColor.ButtonLightGray
            }
            val backgroundColor = if (node.boxSize == 30f) {
                GPColor.MainOrangeColor
            } else {
                GPColor.White
            }
            Box (
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                // 시작 노드
                if (courseInfo.questInfo.questName.startsWith("0")) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .offset(node.drawOffset.x.dp, node.drawOffset.y.dp)
                            .clip(RoundedCornerShape(6.gdp))
                            .background(GPColor.MainOrangeColor)
                            .padding(4.gdp),
                        contentAlignment = Alignment.Center
                    ) {
                        GPText(
                            text = courseInfo.questInfo.questName.split(".").last(),
                            textSize = 10.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                    }
                } else {
                    GPSquircleShapeWithBorder(
                        modifier = Modifier
                            .size(node.boxSize.dp)
                            .offset(node.drawOffset.x.dp, node.drawOffset.y.dp)
                            .noRippleClickable {
                                onExerciseClicked(courseInfo)
                            },
                        backgroundColor = backgroundColor,
                        borderColor = borderColor
                    ) {
                        courseInfo.thumbnailUrl?.let { url ->
                            AsyncImage(
                                modifier = Modifier
                                    .size((node.boxSize * 0.6).dp),
                                model = IMAGE_BASE_URL + url,
                                contentDescription = "운동 이미지"
                            )
                        }
                    }
                }
            }
        }
    }
}