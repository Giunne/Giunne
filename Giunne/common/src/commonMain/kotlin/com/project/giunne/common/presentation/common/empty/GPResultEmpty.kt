package com.project.giunne.common.presentation.common.empty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gsp
import com.project.giunne.image_no_result
import org.jetbrains.compose.resources.painterResource

@Composable
fun GPResultEmpty(
    title: String,
    highlightRange: IntRange
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(GPColor.BackgroundLightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(Res.drawable.image_no_result),
            contentDescription = "이미지"
        )
        GPAnnotatedText(
            text = buildAnnotatedString {
                append(title.substring(0, highlightRange.first))
                withStyle(
                    style = SpanStyle(
                        color = GPColor.MainOrangeColor,
                        fontSize = 16.gsp
                    )
                ) {
                    append(title.substring(highlightRange.first, highlightRange.last + 1))
                }
                append(title.substring(highlightRange.last + 1))
            },
            textSize = 16.gsp,
        )
    }
}