package com.project.giunne.common.presentation.select.content

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.project.giunne.common.presentation.select.state.CharacterUiState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectCharacterPage(
    modifier: Modifier,
    pagerState: PagerState,
    characters: List<CharacterUiState>,
) {
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .border(
                    width = 6.gdp,
                    color = GPColor.MainOrangeColor,
                    shape = RoundedCornerShape(16.gdp)
                )
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .pointerInput(Unit) {
                        // For Desktop
                        detectHorizontalDragGestures { _, scrollAmount ->
                            val direction = if (scrollAmount > 0) -1 else 1
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + direction)
                            }
                        }
                    },
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 60.gdp),
                key = { characters[it].hashCode() }
            ) { index ->
                val character = characters[index]
                val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
                val imageSize by animateFloatAsState(
                    targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                    animationSpec = tween(durationMillis = 300),
                    label = "Select Animation"
                )
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = imageSize
                            scaleY = imageSize
                        },
                    painter = painterResource(character.res),
                    contentDescription = ""
                )
            }
        }
        SelectCharacterLabel(
            characters[pagerState.currentPage].name
        )
    }
}