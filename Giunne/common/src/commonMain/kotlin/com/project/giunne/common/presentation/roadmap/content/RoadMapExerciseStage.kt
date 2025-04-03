package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.StudentCourseInfo
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
import com.project.giunne.icon_lock
import com.project.giunne.icon_plus
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoadMapExerciseStage(
    offset: Offset,
    questInfoList: List<StudentCourseInfo>,
    node: List<Node>,
    connect: List<ConnectNode>,
    onExerciseClicked: (StudentCourseInfo) -> Unit,
) {
    val density = LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(offset.x.dp, offset.y.dp),
    ) {
        DrawExerciseLine(
            density = density,
            questInfoList = questInfoList,
            connect = connect
        )
        node.forEach { node ->
            val courseInfo = questInfoList.find { it.courseName == node.step } ?: StudentCourseInfo()
            val questStateInfo = courseInfo.questInfo.questStateInfo
            val status = questStateInfo.questProgress
            val isBonus = questStateInfo.hasExtraPoints
            val starPoint = courseInfo.questInfo.questStateInfo.starPoint
            val borderColor = if (node.boxSize == 30f || status == "CONFIRM") {
                GPColor.MainOrangeColor
            } else if (status == "CHECK" || status == "UPLOAD") {
                GPColor.Yellow
            } else {
                GPColor.ButtonLightGray
            }
            val backgroundColor = if (node.boxSize == 30f) {
                GPColor.MainOrangeColor
            } else if (status == "LOCK") {
                GPColor.TextBlack
            } else if (status == "LOCK_OPEN") {
                GPColor.TextGray
            } else {
                GPColor.White
            }
            Box(
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
                                if (status != "LOCK") {
                                    onExerciseClicked(courseInfo)
                                }
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

                        RoadMapGradeCount(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.gdp)
                                .height(20.gdp)
                                .offset(y = 8.gdp)
                                .align(Alignment.BottomCenter),
                            starPoint = starPoint
                        )
                    }
                    if (status == "LOCK") {
                        Icon(
                            modifier = Modifier
                                .size(18.gdp)
                                .offset(node.drawOffset.x.dp, node.drawOffset.y.dp),
                            painter = painterResource(Res.drawable.icon_lock),
                            contentDescription = "잠금",
                            tint = GPColor.MainOrangeColor
                        )
                    }

                    if (isBonus) {
                        Icon(
                            modifier = Modifier
                                .size(8.gdp)
                                .offset(node.drawOffset.x.dp + node.boxSize.dp / 4, node.drawOffset.y.dp - node.boxSize.dp / 4),
                            painter = painterResource(Res.drawable.icon_plus),
                            contentDescription = "추가동작",
                            tint = GPColor.TextBlack_232323
                        )
                    }
                }
            }
        }
    }
}