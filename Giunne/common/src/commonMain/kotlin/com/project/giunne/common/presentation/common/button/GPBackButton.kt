package com.project.giunne.common.presentation.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_back_arrow
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun GPBackButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .noRippleClickable(
                interactionSource = interactionSource
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.height(18.gdp),
            painter = painterResource(Res.drawable.icon_back_arrow),
            contentDescription = null,
        )
    }
}