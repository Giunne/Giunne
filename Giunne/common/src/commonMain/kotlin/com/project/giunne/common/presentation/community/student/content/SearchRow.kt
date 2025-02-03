package com.project.giunne.common.presentation.community.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_lock
import com.project.giunne.icon_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchRow(
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") } /* TODO MVI */

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPTextField(
            modifier = Modifier
                .padding(vertical = 4.gdp)
                .shadow(
                    2.gdp,
                    RoundedCornerShape(12.gdp)
                )
                .weight(152f)
                .background(color = GPColor.White),
            shape = RoundedCornerShape(12.gdp),
            textStyle = TextStyle(
                color = GPColor.TextBlack,
                fontSize = 16.gsp,
                fontFamily = GPFontFamily.Medium
            ),
            value = searchText,
            onValueChange = { searchText = it },
            paddingHorizontal = 12.gdp,
            placeholder = {
                GPText(
                    text = "이름",
                    textSize = 16.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextLightGray
                )
            },
            prefix = {
                Image(
                    modifier = Modifier.size(18.gdp),
                    painter = painterResource(Res.drawable.icon_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = GPColor.ButtonGray),
                    contentScale = ContentScale.FillHeight
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                }
            ),
        )
        SpW(6.gdp)
        Box(
            modifier = Modifier
                .padding(vertical = 4.gdp)
                .shadow(
                    2.gdp,
                    RoundedCornerShape(12.gdp)
                )
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                )
                .weight(80f)
                .fillMaxHeight()
        )
        SpW(6.gdp)
        Box(
            modifier = Modifier
                .padding(vertical = 4.gdp)
                .shadow(
                    2.gdp,
                    RoundedCornerShape(12.gdp)
                )
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                )
                .weight(80f)
                .fillMaxHeight()
        )
    }
}