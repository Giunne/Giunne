package com.project.giunne.common.presentation.home.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_next
import org.jetbrains.compose.resources.painterResource

@Composable
fun BorderButton(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(color = GPColor.Transparent)
            .border(BorderStroke(1.gdp, GPColor.MainOrangeColor), RoundedCornerShape(12.gdp))
            .padding(14.gdp)
            .noRippleClickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        GPText(
            text = title,
            textSize = 12.gsp,
            fontFamily = GPFontFamily.Bold,
            textColor = GPColor.MainOrangeColor
        )
        content()
    }
}