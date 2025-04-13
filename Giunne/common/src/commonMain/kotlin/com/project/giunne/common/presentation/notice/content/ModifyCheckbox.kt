package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.checkbox.GPCheckbox
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ModifyCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable {
                onCheckedChanged(!isChecked)
            }
            .padding(vertical = 4.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GPText(
            text = "수정하기",
            textSize = 11.gsp
        )
        GPCheckbox(
            modifier = Modifier.size(32.gdp),
            isChecked = isChecked,
            onCheckedChanged = {
                onCheckedChanged(it)
            }
        )
    }
}