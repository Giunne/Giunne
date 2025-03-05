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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.shape.GPSquircleShapeWithBorder
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.content.DrawJoggingLine
import com.project.giunne.common.presentation.roadmap.dummy.joggingUiState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_check
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherJoggingRoadmapScreen(
    modifier: Modifier,
) {
    val density = LocalDensity.current.density
    val joggingWeek = listOf(
        13, 14, 15,
        12, 11, 10,
        7, 8, 9,
        6, 5, 4,
        1, 2, 3
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
        DrawJoggingLine(
            modifier = Modifier
                .width(360.gdp)
                .height(500.gdp)
                .padding(48.gdp),
            joggingUiState = joggingUiState,
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
            itemsIndexed(joggingWeek) { index, week ->
                GPSquircleShapeWithBorder(
                    modifier = Modifier.size(64.gdp),
                    backgroundColor = if (week < joggingUiState.week) GPColor.Green else GPColor.White,
                    borderColor = if (week <= joggingUiState.week) {
                        GPColor.Green
                    } else if (joggingUiState.bonusWeek.contains(week)) {
                        GPColor.MainOrangeColor
                    } else {
                        GPColor.BorderLightGray
                    },
                ) {
                    if (week < joggingUiState.week) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_check),
                            contentDescription = "성공",
                            tint = GPColor.White
                        )
                    } else {
                        GPText(
                            text = "${week}주차",
                        )
                    }
                }
            }
        }
    }
}