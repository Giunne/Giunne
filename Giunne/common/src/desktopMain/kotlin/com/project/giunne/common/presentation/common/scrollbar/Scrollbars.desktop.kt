package com.project.giunne.common.presentation.common.scrollbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: LazyListState
) {
    androidx.compose.foundation.VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(state),
        style = ScrollbarStyle(
            minimalHeight = 16.gdp,
            thickness = 8.gdp,
            shape = RoundedCornerShape(4.gdp),
            hoverDurationMillis = 300,
            unhoverColor = GPColor.TextLightGray.copy(alpha = 0.4f),
            hoverColor = GPColor.TextLightGray.copy(alpha = 0.7f)
        )
    )
}

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: LazyGridState
) {
    androidx.compose.foundation.VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(state),
        style = ScrollbarStyle(
            minimalHeight = 16.gdp,
            thickness = 8.gdp,
            shape = RoundedCornerShape(4.gdp),
            hoverDurationMillis = 300,
            unhoverColor = GPColor.TextLightGray.copy(alpha = 0.4f),
            hoverColor = GPColor.TextLightGray.copy(alpha = 0.7f)
        )
    )
}

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: ScrollState
) {
    androidx.compose.foundation.VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(state),
        style = ScrollbarStyle(
            minimalHeight = 16.gdp,
            thickness = 8.gdp,
            shape = RoundedCornerShape(4.gdp),
            hoverDurationMillis = 300,
            unhoverColor = GPColor.TextLightGray.copy(alpha = 0.4f),
            hoverColor = GPColor.TextLightGray.copy(alpha = 0.7f)
        )
    )
}

@Composable
actual fun HorizontalScrollbar(
    modifier: Modifier,
    state: LazyListState
) {
    androidx.compose.foundation.HorizontalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(state),
        style = ScrollbarStyle(
            minimalHeight = 16.gdp,
            thickness = 8.gdp,
            shape = RoundedCornerShape(4.gdp),
            hoverDurationMillis = 300,
            unhoverColor = GPColor.TextLightGray.copy(alpha = 0.4f),
            hoverColor = GPColor.TextLightGray.copy(alpha = 0.7f)
        )
    )
}