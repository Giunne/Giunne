package com.project.giunne.common.presentation.common.checkbox

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun GPCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Checkbox(
        modifier = modifier,
        checked = isChecked,
        colors = CheckboxDefaults.colors(
            checkedColor = GPColor.MainOrangeColor,
            uncheckedColor = GPColor.ButtonLightGray,
            checkmarkColor = GPColor.White
        ),
        onCheckedChange = {
            onCheckedChanged(it)
        }
    )
}