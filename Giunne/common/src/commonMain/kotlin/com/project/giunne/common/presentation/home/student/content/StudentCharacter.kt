package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.presentation.common.charactor.GPCharacter
import com.project.giunne.common.presentation.common.progress.GPLevelProgressBar
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentCharacter(
    level: Int,
    currentExp: Int,
    totalExp: Int,
    character: String,
    items: List<Item>
) {
    val percent = currentExp.toFloat() / totalExp.toFloat()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.gdp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            LevelBadgeBox(
                modifier = Modifier.padding(top = 8.gdp),
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
            GPCharacter(
                modifier = Modifier.size(256.gdp),
                character = character,
                items = items
            )

            Spacer(modifier = Modifier.height(10.gdp))
            Column {
                ExpArrowPercent(
                    percent = percent
                )
                Spacer(modifier = Modifier.height(4.gdp))
                GPLevelProgressBar(
                    percent = percent
                )
                Spacer(modifier = Modifier.height(4.gdp))
                GPText(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 4.gdp),
                    text = "$currentExp / $totalExp",
                    textAlign = TextAlign.End,
                    textSize = 12.gsp
                )
            }
        }
    }
}