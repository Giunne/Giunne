package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.checkbox.GPCheckbox
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun TeacherCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .noRippleClickable {
                onCheckedChanged(!isChecked)
            }
            .padding(8.gdp)
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        GPText(
            textSize = 12.gsp,
            text = "학생 운동 체크하기"
        )
        GPCheckbox(
            isChecked = isChecked,
            onCheckedChanged = onCheckedChanged
        )
    }
}