package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.QuestStateInfo
import com.project.giunne.common.presentation.common.checkbox.GPCheckbox
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
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
            .padding(16.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GPText(
            text = studentCheck.name,
            textSize = 14.gsp
        )
        GPCheckbox(
            isChecked = studentCheck.isChecked,
            onCheckedChanged = {
                onCheckedChanged(studentCheck, it)
            }
        )
    }
}