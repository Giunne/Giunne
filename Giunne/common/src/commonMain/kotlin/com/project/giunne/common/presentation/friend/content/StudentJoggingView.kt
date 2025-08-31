package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalDensity
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.presentation.common.shape.GPSquircleShapeWithBorder
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.content.DrawJoggingLine
import com.project.giunne.common.presentation.roadmap.content.RoadMapGradeCount
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_check
import com.project.giunne.icon_lock
import com.project.giunne.icon_plus
import org.jetbrains.compose.resources.painterResource

@Composable
fun StudentJoggingView(
    modifier: Modifier,
    questInfoList: List<StudentCourseInfo>
) {
    val lazyGridState = rememberLazyGridState()
    val density = LocalDensity.current.density
    val joggingWeek = listOf(
        "30주차", "29주차", "28주차",
        "25주차", "26주차", "27주차",
        "24주차", "23주차", "22주차",
        "19주차", "20주차", "21주차",
        "18주차", "17주차", "16주차",
        "13주차", "14주차", "15주차",
        "12주차", "11주차", "10주차",
        "7주차", "8주차", "9주차",
        "6주차", "5주차", "4주차",
        "1주차", "2주차", "3주차",
    )

    Column(
        modifier = modifier
            .background(GPColor.BackgroundLightGray)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Line 그리기
            DrawJoggingLine(
                modifier = Modifier
                    .clipToBounds()
                    .width(360.gdp)
                    .height(500.gdp)
                    .padding(48.gdp),
                lazyGridState = lazyGridState,
                questInfoList = questInfoList,
                weeks = joggingWeek,
                boxSize = 64.gdp.value * density,
                spacing = 16.gdp.value * density
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .width(360.gdp)
                    .height(500.gdp),
                state = lazyGridState,
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(48.gdp),
                horizontalArrangement = Arrangement.spacedBy(16.gdp),
                verticalArrangement = Arrangement.spacedBy(16.gdp),
            ) {
                items(joggingWeek) { week ->
                    val courseInfo =
                        questInfoList.find { it.courseName == week } ?: StudentCourseInfo()
                    val questStateInfo = courseInfo.questInfo.questStateInfo
                    val status = questStateInfo.questProgress
                    val isBonus = questStateInfo.hasExtraPoints
                    val starPoint = courseInfo.questInfo.questStateInfo.starPoint

                    GPSquircleShapeWithBorder(
                        modifier = Modifier.size(64.gdp),
                        backgroundColor = when (status) {
                            "LOCK_OPEN" -> {
                                GPColor.TextBlack
                            }

                            else -> {
                                GPColor.White
                            }
                        },
                        borderColor = when (status) {
                            "CONFIRM" -> {
                                GPColor.MainOrangeColor
                            }

                            "CHECK", "UPLOAD" -> {
                                GPColor.Yellow
                            }

                            else -> {
                                GPColor.BorderLightGray
                            }
                        },

                        ) {
                        GPText(
                            text = week,
                        )
                        when (status) {
                            "CONFIRM" -> {
                                if (isBonus) {
                                    Icon(
                                        modifier = Modifier
                                            .size(10.gdp)
                                            .align(Alignment.TopEnd)
                                            .offset(x = (-8).gdp, y = 8.gdp),
                                        painter = painterResource(Res.drawable.icon_plus),
                                        contentDescription = "추가동작",
                                        tint = GPColor.TextBlack_232323
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

                            "LOCK_OPEN" -> {
                                Icon(
                                    modifier = Modifier
                                        .size(24.gdp),
                                    painter = painterResource(Res.drawable.icon_lock),
                                    contentDescription = "잠금",
                                    tint = GPColor.MainOrangeColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}