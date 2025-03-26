package com.project.giunne.common.presentation.mypage.student.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.home.common.RowWithDropShadow

@Composable
fun MyPageStudentInfoColumn(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    RowWithDropShadow(
        modifier = modifier
    ) {
        Column {
            content()
        }
    }
}