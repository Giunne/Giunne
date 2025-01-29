package com.project.giunne.common.presentation.signup.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_arrow_right
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignupTypeSelectButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    image: Painter,
    text: String,
    onClick: () -> Unit,
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    val buttonFillColor by animateColorAsState(
        targetValue = if (isPressed) GPColor.ButtonPressWhite else GPColor.White
    )

    Row(
        modifier = modifier
            .background(color = buttonFillColor, shape = RoundedCornerShape(12.gdp))
            .noRippleClickable(interactionSource = interactionSource) { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.gdp)
                .background(
                    color = GPColor.BackgroundFrameOrange,
                    shape = RoundedCornerShape(12.gdp)
                ),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = image,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        GPText(
            text = text,
            textSize = 12.gsp,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.gdp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.height(16.gdp),
                painter = painterResource(Res.drawable.icon_arrow_right),
                contentDescription = null,
            )
        }
    }
}