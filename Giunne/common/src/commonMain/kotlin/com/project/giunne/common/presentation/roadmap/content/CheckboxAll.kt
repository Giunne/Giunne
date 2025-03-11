package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.checkbox.GPCheckbox
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun CheckboxAll(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable {
                onCheckedChanged(!isChecked)
            }
            .padding(horizontal = 16.gdp, vertical = 4.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GPText(
            text = if (isChecked) "전체 해제" else "전체 선택",
            textSize = 14.gsp
        )
        GPCheckbox(
            isChecked = isChecked,
            onCheckedChanged = {
                onCheckedChanged(it)
            }
        )
    }
}