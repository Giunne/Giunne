package com.project.giunne.common.presentation.mypage.student.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.WearingItem
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.presentation.common.progress.GPLevelProgressBar
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.RowWithDropShadow
import com.project.giunne.common.presentation.home.student.content.LevelBadgeBox
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun MyPageCharacter(
    modifier: Modifier,
    wearingItems: List<WearingItem>,
    percent: Float,
    level: Int
) {
    val currentCharacter = wearingItems.find { it.categoryId == 1 }
    val currentCharacterUrl = currentCharacter?.itemImage?.fileUrl.orEmpty()
    val nextLevelCharacterUrl = if (currentCharacterUrl.isNotEmpty() && currentCharacterUrl.contains("7").not()) {
        currentCharacterUrl.replace(currentCharacter?.itemImage?.level.toString(),
            currentCharacter?.itemImage?.level?.plus(1).toString()
        )
    } else {
        ""
    }
    RowWithDropShadow(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LevelBadgeBox(
                    modifier = Modifier.padding(top = 8.gdp),
                    level = level
                )
                GPText(
                    text = "${percent * 100}%"
                )
            }
            Spacer(modifier = Modifier.height(8.gdp))
            GPLevelProgressBar(
                percent = percent
            )
        }

        Spacer(modifier = Modifier.width(8.gdp))

        if (nextLevelCharacterUrl.isNotEmpty()) {
            GPSquircleShape(
                modifier = Modifier
                    .size(64.gdp)
                    .blur(
                        radiusX = 4.gdp,
                        radiusY = 4.gdp
                    ),
                backgroundColor = GPColor.BackgroundLightGray
            ) {
                AsyncImage(
                    modifier = Modifier.size((64 / 2).gdp),
                    model = IMAGE_BASE_URL + nextLevelCharacterUrl,
                    contentDescription = "다음 레벨 캐릭터"
                )
            }
        }
    }
}