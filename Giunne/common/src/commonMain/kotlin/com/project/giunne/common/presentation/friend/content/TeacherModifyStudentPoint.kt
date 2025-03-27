package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define.playerId
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun TeacherModifyStudentPoint(
    point: String,
    onPointChanged: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.gdp)
    ) {
        Box(
            modifier = Modifier.size(16.gdp)
                .background(shape = CircleShape, color = GPColor.MainOrangeColor),
            contentAlignment = Alignment.Center
        ) {
            GPText(
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White,
                text = "C",
                textSize = 10.gsp,
            )
        }
        GPTextField(
            modifier = Modifier
                .height(24.gdp)
                .width(64.gdp),
            shape = RoundedCornerShape(12.gdp),
            textStyle = TextStyle(
                color = GPColor.TextBlack,
                fontSize = 10.gsp,
                fontFamily = GPFontFamily.Bold
            ),
            value = point,
            onValueChange = {
                if (it.length <= 8 && it.all { it.isDigit() }) {
                    onPointChanged(it)
                }
            },
            paddingHorizontal = 8.gdp,
            border = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onPointChanged(point)
                }
            ),
        )
    }
}