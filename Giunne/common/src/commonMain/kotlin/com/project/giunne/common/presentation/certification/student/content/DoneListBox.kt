package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.certification.student.dummy.RoadmapDoneDto
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun DoneListBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {  }
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(start = 4.gdp)
            ) {
                GPText(
                    modifier = Modifier.padding(start = 4.gdp),
                    text = "완료 내역", /* TODO string */
                    textSize = 18.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.TextBlack
                )
            }
            SpH(10.gdp)
            content()
        }
    }
}