package com.project.giunne.common.presentation.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.content.LottieBox
import com.project.giunne.common.presentation.shop.content.MysteryBox
import com.project.giunne.common.presentation.shop.dummy.gachaItems
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun PickingItemScreen(
    modifier: Modifier,
    onWearingItemClick: () -> Unit = {},
) {
    var isProgress by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        isProgress = false
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.gdp))

        if (isProgress) {
            MysteryBox(
                modifier = Modifier.wrapContentSize()
            )
        } else {
            Box(
                contentAlignment = Alignment.Center
            ) {
                LottieBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    filePath = "files/animation_party_background.json"
                )

                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.gdp)
                ) {
                    Image(
                        modifier = Modifier.size(120.gdp),
                        painter = painterResource(gachaItems[3]),
                        contentDescription = "이미지"
                    )

                    GPText(
                        text = "노란색 우비 당첨!",
                        textSize = 18.gsp,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            GPButton(
                modifier = Modifier
                    .padding(horizontal = 16.gdp)
                    .fillMaxWidth()
                    .height(48.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = onWearingItemClick,
            ) {
                GPText(
                    text = "착용하러 가기",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}