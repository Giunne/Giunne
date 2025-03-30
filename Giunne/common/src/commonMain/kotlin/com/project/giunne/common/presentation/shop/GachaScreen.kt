package com.project.giunne.common.presentation.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.project.giunne.common.data.remote.request.GachaType
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.toggle.GPToggleButton
import com.project.giunne.common.presentation.shop.content.GachaButtonView
import com.project.giunne.common.presentation.shop.content.GachaItemList
import com.project.giunne.common.presentation.shop.content.sRankColorBrush
import com.project.giunne.common.presentation.shop.intent.GachaStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GachaScreen(
    onGachaClick: (GachaType) -> Unit
) {
    var isAdvanced by remember { mutableStateOf(false) }
    val gachaStore by remember { mutableStateOf(GachaStore()) }
    val gachaState by gachaStore.uiState.collectAsState()

    LaunchedEffect(Unit) {
        gachaStore.getGachaType()
        AvatarUtil.getMyPointInfo()
    }

    if (gachaState.loading) {
        Loader()
    } else {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                GPToggleButton(
                    modifier = Modifier.width(120.gdp)
                        .height(56.gdp)
                        .padding(horizontal = 16.gdp, vertical = 8.gdp),
                    titleLeft = gachaState.generalGachaInfo.codeName,
                    titleRight = gachaState.premiumGachaInfo.codeName,
                    isSelected = isAdvanced,
                    onLeftButtonClick = {
                        isAdvanced = false
                    },
                    onRightButtonClick = {
                        isAdvanced = true
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.gdp))

            if (!isAdvanced) {
                GPText(
                    text = "내 캐릭터를 꾸며줄",
                    textSize = 24.gsp,
                    fontFamily = GPFontFamily.Bold
                )

                Spacer(modifier = Modifier.height(8.gdp))

                GPText(
                    text = "아이템을 뽑아보세요!",
                    textSize = 24.gsp,
                    fontFamily = GPFontFamily.Bold
                )
            } else {
                GPText(
                    text = "내 캐릭터를 꾸며줄",
                    style = TextStyle(
                        brush = sRankColorBrush
                    ),
                    textSize = 24.gsp,
                    fontFamily = GPFontFamily.Bold
                )

                Spacer(modifier = Modifier.height(8.gdp))

                GPText(
                    text = "아이템을 뽑아보세요!",
                    style = TextStyle(
                        brush = sRankColorBrush
                    ),
                    textSize = 24.gsp,
                    fontFamily = GPFontFamily.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.gdp))

            GPText(
                text = if (!isAdvanced) "${gachaState.generalGachaInfo.imageList.size}개 아이템 중 하나 당첨" // 일반이면
                    else "${gachaState.premiumGachaInfo.imageList.size}개 아이템 중 하나 당첨", // 고급이면
                textSize = 18.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.ButtonLightGray
            )

            Spacer(modifier = Modifier.height(64.gdp))

            GachaItemList(
                gachaItems = if (!isAdvanced) gachaState.generalGachaInfo.imageList // 일반이면
                    else gachaState.premiumGachaInfo.imageList, // 고급이면
            )

            Spacer(modifier = Modifier.weight(1f))

            GachaButtonView(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.gdp),
                gachaCost = if (isAdvanced) gachaState.premiumGachaInfo.price else gachaState.generalGachaInfo.price,
                remainPoint = AvatarUtil.uiState.value.myPoint,
                onGachaClick = {
                    onGachaClick(if(!isAdvanced) GachaType.GENERAL else GachaType.PREMIUM)
                },
            )
        }
    }
}