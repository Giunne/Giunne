package com.project.giunne.common.presentation.home.teacher.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextDecoration
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.BorderButton
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_code
import org.jetbrains.compose.resources.painterResource

@Composable
fun StudentSignUpCodeBox(
    modifier: Modifier,
    signUpCode: String,
    onCopyCode: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.gdp)
            .shadow(
                shape = RoundedCornerShape(16.gdp),
                ambientColor = GPColor.TextBlack.copy(alpha = 0.05f),
                elevation = 1.gdp
            )
            .clip(RoundedCornerShape(16.gdp))
            .background(GPColor.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(GPColor.TextBlack)
                .padding(vertical = 8.gdp),
            contentAlignment = Alignment.Center
        ) {
            GPText(
                text = "학생 회원가입 코드",
                textColor = GPColor.White,
                textSize = 12.gsp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.gdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GPSquircleShape(
                modifier = Modifier
                    .size(56.gdp),
                backgroundColor = GPColor.BackgroundLightGray
            ) {
                Icon(
                    modifier = Modifier.size((56 / 3).gdp),
                    tint = GPColor.MainOrangeColor,
                    painter = painterResource(Res.drawable.icon_code),
                    contentDescription = null
                )
            }
            GPText(
                text = signUpCode,
                textColor = GPColor.TextBlack,
                textSize = 15.gsp,
                textDecoration = TextDecoration.Underline
            )
            BorderButton(
                modifier = Modifier.wrapContentSize(),
                title = "복사하기",
                onClick = onCopyCode
            )
        }
    }
}