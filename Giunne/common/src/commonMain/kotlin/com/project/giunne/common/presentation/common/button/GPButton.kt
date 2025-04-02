package com.project.giunne.common.presentation.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.util.gdp

@Composable
internal fun GPButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    normalColor: Color,
    pressColor: Color,
    hoverColor: Color? = null,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(12.gdp),
    content: @Composable (RowScope.() -> Unit),
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier
            .background(
                color = when {
                    isPressed -> pressColor
                    isHovered -> hoverColor ?: normalColor
                    else -> normalColor
                },
                shape = shape
            )
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .noRippleClickable(
                interactionSource = interactionSource
            ) { onClick() }
            .padding(horizontal = 16.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
internal fun GPButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    normalColor: Color,
    pressColor: Color,
    hoverColor: Color? = null,
    onClick: () -> Unit,
    horizontalPadding: Dp,
    shape: Shape = RoundedCornerShape(12.gdp),
    content: @Composable (RowScope.() -> Unit),
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier
            .background(
                color = when {
                    isPressed -> pressColor
                    isHovered -> hoverColor ?: normalColor
                    else -> normalColor
                },
                shape = shape
            )
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .noRippleClickable(
                interactionSource = interactionSource
            ) { onClick() }
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        content()
    }
}