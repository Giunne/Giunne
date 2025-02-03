package com.project.giunne.common.presentation.common.charactor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.shop.dummy.TestItem
import com.project.giunne.common.presentation.shop.state.ItemType
import com.project.giunne.common.util.gdp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GPCharacter(
    modifier: Modifier = Modifier,
    character: DrawableResource,
    items: List<TestItem>
) {
    Box {
        Image(
            modifier = modifier,
            painter = painterResource(character),
            contentDescription = null
        )
        items.forEach { item ->
            if (item.type != ItemType.CHARACTER) {
                Image(
                    modifier = Modifier
                        .size(
                            width = item.size.width.gdp,
                            height = item.size.height.gdp
                        )
                        .offset(
                            x = item.offsetX.gdp,
                            y = item.offsetY.gdp
                        ),
                    painter = painterResource(item.image),
                    contentDescription = ""
                )
            }
        }
    }
}