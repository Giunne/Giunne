package com.project.giunne.common.presentation.common.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor

@Composable
fun GPTextFieldWithClose(
    modifier: Modifier,
    focusManager: FocusManager = LocalFocusManager.current,
    placeHolder: String,
    text: String,
    leadingIcon: @Composable () -> Unit = {},
    onTextChange: (String) -> Unit,
    onClear: () -> Unit

) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(GPColor.White)
            .border(
                width = 2.dp,
                color = GPColor.BorderLightGray,
                shape = RoundedCornerShape(12.dp)
            ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            leadingIcon()
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = onClear
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "지우기"
                    )
                }
            }
        },
        placeholder = {
            GPText(
                text = placeHolder,
                textSize = 14.sp
            )
        }
    )
}