package com.project.giunne.common.presentation.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.content.GachaButtonView
import com.project.giunne.common.presentation.shop.content.GachaItemList
import com.project.giunne.common.presentation.shop.dummy.gachaItems
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GachaScreen(
    /* TODO(API 나오면 전체 아이템 목록으로 변경) */
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(64.gdp))

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

        Spacer(modifier = Modifier.height(32.gdp))

        GPText(
            text = "${gachaItems.size}개 아이템 중 하나 당첨",
            textSize = 18.gsp,
            fontFamily = GPFontFamily.Bold,
            textColor = GPColor.ButtonLightGray
        )

        Spacer(modifier = Modifier.height(64.gdp))

        GachaItemList(
            gachaItems = gachaItems
        )

        Spacer(modifier = Modifier.weight(1f))

        GachaButtonView(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.gdp, vertical = 8.gdp),
            gachaCost = 100,
            remainPoint = 240,
            onGachaClick = {}
        )
    }
}