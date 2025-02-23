package com.project.giunne.common.presentation.common.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GPSearchBar(
    modifier: Modifier,
    focusManager: FocusManager,
    placeHolder: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchQuery: () -> Unit,
    onClear: () -> Unit

) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.gdp))
            .background(GPColor.White)
            .border(
                width = 1.gdp,
                color = GPColor.BackgroundGray_EBEBEB,
                shape = RoundedCornerShape(12.gdp)
            ),
        maxLines = 1,
        shape = RoundedCornerShape(12.gdp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchQuery()
                focusManager.clearFocus()
            }
        ),
        value = searchText,
        onValueChange = {
            onSearchTextChange(it)
        },
        leadingIcon = {
            IconButton(
                onClick = onSearchQuery
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "검색"
                )
            }
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
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
                textSize = 14.gsp,
                textColor = GPColor.TextLightGray
            )
        }
    )
}