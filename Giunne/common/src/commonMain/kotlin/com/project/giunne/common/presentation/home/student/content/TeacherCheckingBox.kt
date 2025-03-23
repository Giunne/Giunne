package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.presentation.home.common.BorderButton
import com.project.giunne.common.presentation.home.common.RowWithDropShadow
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_next
import com.project.giunne.icon_student_check
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherCheckingBox(
    modifier: Modifier,
    teacherStateTitle: String,
    onClickCommunity: () -> Unit
) {
    RowWithDropShadow(
        modifier = modifier
            .padding(horizontal = 16.gdp)
    ) {
        GPSquircleShape(
            modifier = Modifier
                .size(56.gdp),
            backgroundColor = GPColor.BackgroundLightGray
        ) {
            Icon(
                modifier = Modifier.size((56 / 3).gdp),
                tint = GPColor.MainOrangeColor,
                painter = painterResource(Res.drawable.icon_student_check),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(10.gdp))

        GPAnnotatedText(
            text = buildAnnotatedString {
                append("선생님이\n")
                withStyle(
                    style = SpanStyle(
                        color = GPColor.MainOrangeColor,
                        fontSize = 18.gsp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(teacherStateTitle)
                }
                append("이에요!")
            },
        )

        Spacer(modifier = Modifier.weight(1f))

        BorderButton(
            modifier = Modifier.wrapContentSize(),
            title = "보러가기",
            content = {
                Icon(
                    modifier = Modifier.height(12.gdp),
                    painter = painterResource(Res.drawable.icon_next),
                    contentDescription = "현황 보러가기",
                    tint = GPColor.MainOrangeColor
                )
            },
            onClick = onClickCommunity
        )
    }
}