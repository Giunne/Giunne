package com.project.giunne.common.presentation.roadmap.student

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
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_check
import org.jetbrains.compose.resources.painterResource

@Composable
fun JoggingRoadmapScreen(
    modifier: Modifier,
    joggingWeeks: List<String>,
    successWeekIndexSet: HashSet<Int>,
    currentWeek: Int,
) {
    val density = LocalDensity.current.density

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
            weeks = joggingWeeks,
            successWeekIndexSet = successWeekIndexSet,
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
            itemsIndexed(joggingWeeks) { index, title ->
                GPSquircleShapeWithBorder(
                    modifier = Modifier.size(64.gdp),
                    backgroundColor = if (successWeekIndexSet.contains(index)) GPColor.Green else GPColor.White,
                    borderColor = if (successWeekIndexSet.contains(index) || index == currentWeek) GPColor.Green else GPColor.BorderLightGray,
                ) {
                    if (successWeekIndexSet.contains(index)) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_check),
                            contentDescription = "성공",
                            tint = GPColor.White
                        )
                    } else {
                        GPText(
                            text = title,
                        )
                    }
                }
            }
        }
    }
}