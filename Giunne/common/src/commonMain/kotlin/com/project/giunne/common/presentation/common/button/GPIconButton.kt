package com.project.giunne.common.presentation.common.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.util.gdp

@Composable
fun GPIconButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: @Composable BoxScope.() -> Unit,
    normalColor: Color,
    pressColor: Color,
    hoverColor: Color? = null,
    shape: Shape = RoundedCornerShape(8.gdp),
    onClick: () -> Unit,
    shadow: Boolean = true,
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val buttonFillColor by animateColorAsState(
        targetValue = when {
            isPressed -> pressColor
            isHovered -> hoverColor ?: normalColor
            else -> normalColor
        },
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = if (shadow) {
                    if(isPressed) 0.gdp else 2.gdp
                } else 0.gdp,
                shape = shape,
            )
            .background(
                color = buttonFillColor,
                shape = shape
            )
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .noRippleClickable(
                interactionSource = interactionSource
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}
