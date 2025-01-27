package com.project.giunne.common.presentation.common.topbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPTitleText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_back_arrow
import org.jetbrains.compose.resources.painterResource

@Composable
fun GPTopBar(
    modifier: Modifier = Modifier,
    onBackButtonClicked: (() -> Unit)? = null,
    titleText: String? = null
) {
    val animateAlpha by animateFloatAsState(
        targetValue = if (titleText != null) 1f else 0f
    )

    Box(
        modifier = modifier
            .height(52.gdp)
            .fillMaxWidth()
    ) {
        if (onBackButtonClicked != null) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.gdp)
                    .alpha(animateAlpha)
                    .noRippleClickable {
                        onBackButtonClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.height(18.gdp),
                    painter = painterResource(Res.drawable.icon_back_arrow),
                    contentDescription = null,
                )
            }
        }
        if (titleText != null) {
            GPTitleText(
                modifier = Modifier.align(Alignment.Center).alpha(animateAlpha),
                text = titleText,
                textSize = 18.gsp,
                textColor = GPColor.TextBlack,
                fontFamily = GPFontFamily.Bold
            )
        }
    }
}