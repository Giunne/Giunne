package com.project.giunne.common.presentation.mypage.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import com.project.giunne.common.presentation.common.progress.GPLevelProgressBar
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.RowWithDropShadow
import com.project.giunne.common.presentation.home.student.content.LevelBadgeBox
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/* TODO("캐릭터 Spec 정해지면 교체") */
@Composable
fun MyPageCharacter(
    modifier: Modifier,
    nextLevelCharacter: DrawableResource,
    percent: Float,
    level: Int
) {
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

        GPSquircleShape(
            modifier = Modifier
                .size(64.gdp)
                .blur(
                    radiusX = 4.gdp,
                    radiusY = 4.gdp
                ),
            backgroundColor = GPColor.BackgroundLightGray
        ) {
            Image(
                modifier = Modifier.size((64 / 2).gdp),
                painter = painterResource(nextLevelCharacter),
                contentDescription = "다음 레벨 캐릭터"
            )
        }
    }
}