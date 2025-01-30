package com.project.giunne.common.presentation.common.badge

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_badge
import org.jetbrains.compose.resources.painterResource

@Composable
fun GPNotificationBadge(
    modifier: Modifier = Modifier,
    count: Int = 0,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .noRippleClickable(interactionSource = interactionSource) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        BadgedBox(
            badge = {
                if (0 < count) {
                    Badge(
                        modifier = Modifier.offset(x = (0).gdp,y = (-4).gdp),
                        containerColor = GPColor.MainOrangeColor,
                        contentColor = Color.White
                    ) {
                        val formatCount = if (100 <= count) { "99.." } else count
                        Text(text = "$formatCount")
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(Res.drawable.icon_badge),
                contentDescription = "notification"
            )
        }
    }
}