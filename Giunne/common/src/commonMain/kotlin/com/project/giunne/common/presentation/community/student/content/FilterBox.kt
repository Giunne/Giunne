package com.project.giunne.common.presentation.community.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_dropdown_expanded
import org.jetbrains.compose.resources.painterResource

@Composable
fun FilterBox(
    modifier: Modifier = Modifier,
    content: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(vertical = 4.gdp)
            .shadow(
                2.gdp,
                RoundedCornerShape(12.gdp)
            )
            .background(
                color = GPColor.White,
                shape = RoundedCornerShape(12.gdp)
            )
            .fillMaxHeight()
            .noRippleClickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.gdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GPText(
                modifier = Modifier.weight(1f),
                text = content,
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack,
                maxLines = 1
            )
            Image(
                modifier = Modifier.height(6.gdp),
                painter = painterResource(Res.drawable.icon_dropdown_expanded),
                contentDescription = "열기 아이콘"
            )
        }
    }
}