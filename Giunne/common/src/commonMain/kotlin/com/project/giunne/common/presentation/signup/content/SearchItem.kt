package com.project.giunne.common.presentation.signup.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun SearchItem(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: String,
    onClickItem: () -> Unit,
    isSelected: Boolean
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    val buttonFillColor by animateColorAsState(
        targetValue = if (isPressed) GPColor.ButtonPressWhite else GPColor.White
    )

    Row(
        modifier = Modifier
            .background(
                color = if (isSelected) GPColor.TextBlack else buttonFillColor
            )
            .fillMaxWidth()
            .height(60.gdp)
            .noRippleClickable(interactionSource = interactionSource) {
                onClickItem()
            }
            .padding(horizontal = 16.gdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPText(
            text = content,
            textColor = if (isSelected) GPColor.White else GPColor.TextBlack,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Medium
        )
    }
    Spacer(modifier = Modifier.height(1.gdp).fillMaxWidth().background(GPColor.BorderLightGray))
}