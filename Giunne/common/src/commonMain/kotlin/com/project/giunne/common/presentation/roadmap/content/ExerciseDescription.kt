package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gsp

@Composable
fun ExerciseDescription(
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        GPText(
            text = description,
            textSize = 14.gsp,
            textAlign = TextAlign.Center,
        )
    }
}