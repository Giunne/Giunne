package com.project.giunne.common.presentation.roadmap.teacher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.project.giunne.common.data.remote.response.CourseInfo
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShapeWithBorder
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.content.DrawTeacherJoggingLine
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun TeacherJoggingRoadmapScreen(
    modifier: Modifier,
    questInfoList: List<CourseInfo>,
    onJoggingClick: (CourseInfo) -> Unit
) {
    val density = LocalDensity.current.density
    val joggingWeek = listOf(
        "13주차", "14주차", "15주차",
        "12주차", "11주차", "10주차",
        "7주차", "8주차", "9주차",
        "6주차", "5주차", "4주차",
        "1주차", "2주차", "3주차",
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        GPText(
            modifier = Modifier.align(Alignment.TopStart),
            text = "열심히 뛰어봅시다!"
        )

        // Line 그리기
        DrawTeacherJoggingLine(
            modifier = Modifier
                .width(360.gdp)
                .height(500.gdp)
                .padding(48.gdp),
            weeks = joggingWeek,
            boxSize = 64.gdp.value * density,
            spacing = 16.gdp.value * density
        )

        LazyVerticalGrid(
            modifier = Modifier
                .width(360.gdp)
                .height(500.gdp),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(48.gdp),
            horizontalArrangement = Arrangement.spacedBy(16.gdp),
            verticalArrangement = Arrangement.spacedBy(16.gdp),
        ) {
            items(joggingWeek) { week ->
                val courseInfo = questInfoList.find { it.courseName == week } ?: CourseInfo()
                GPSquircleShapeWithBorder(
                    modifier = Modifier.size(64.gdp)
                        .noRippleClickable {
                            onJoggingClick(courseInfo)
                        },
                    backgroundColor = GPColor.White,
                    borderColor = GPColor.BorderLightGray
                ) {
                    GPText(
                        text = week
                    )
                }
            }
        }
    }
}