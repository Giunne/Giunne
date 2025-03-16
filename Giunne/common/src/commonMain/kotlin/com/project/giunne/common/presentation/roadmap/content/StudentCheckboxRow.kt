package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.QuestStateInfo
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.checkbox.GPCheckbox
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentCheckboxRow(
    modifier: Modifier = Modifier,
    studentCheck: QuestStateInfo,
    onCheckedChanged: (QuestStateInfo, Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable {
                onCheckedChanged(studentCheck, !studentCheck.isChecked)
            }
            .padding(8.gdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier.size(40.gdp),
            backgroundColor = GPColor.BackgroundFrameOrange,
            content = {
                GPSmallCharacter(
                    modifier = Modifier.fillMaxSize(),
                    wearingItems = studentCheck.wearingItems
                )
            }
        )

        Spacer(modifier = Modifier.width(16.gdp))

        GPText(
            text = studentCheck.name,
            textSize = 13.gsp,
        )

        Spacer(modifier = Modifier.weight(1f))

        GPCheckbox(
            isChecked = studentCheck.isChecked,
            onCheckedChanged = {
                onCheckedChanged(studentCheck, it)
            }
        )
    }
}