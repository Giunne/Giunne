package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.charactor.GPCharacter
import com.project.giunne.common.presentation.common.progress.GPLevelProgressBar
import com.project.giunne.common.presentation.shop.dummy.TestItem
import com.project.giunne.common.util.gdp
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun StudentCharacter(
    level: Int,
    percent: Float,
    character: DrawableResource,
    items: List<TestItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.gdp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            LevelBadgeBox(
                level = level
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(
            modifier = Modifier
                .width(256.gdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.gdp)
        ) {
            /* TODO(캐릭터 Spec 정해지면 변경) */
            GPCharacter(
                modifier = Modifier.size(256.gdp),
                character = character,
                items = items
            )

            Spacer(modifier = Modifier.height(10.gdp))
            GPLevelProgressBar(
                percent = percent
            )
        }
    }
}