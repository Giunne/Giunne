package com.project.giunne.common.presentation.mypage.student.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.RowWithDropShadow
import com.project.giunne.common.presentation.mypage.common.MyPageSettingInfo
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_next
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyPageStudentInfoColumn(
    modifier: Modifier,
    onClickLogOut: () -> Unit
) {
    RowWithDropShadow(
        modifier = modifier
    ) {
        Column {
            MyPageSettingInfo(
                title = "학교",
                content = {
                    GPText(
                        text = "테스트 학교",
                        fontFamily = GPFontFamily.Regular,
                        textSize = 12.gsp
                    )
                }
            )
            HorizontalDivider(
                color = GPColor.BackgroundGray_F6F6F6
            )
            MyPageSettingInfo(
                title = "로그아웃",
                color = GPColor.Red,
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.icon_next),
                        contentDescription = "로그아웃",
                        tint = GPColor.Red
                    )
                },
                onClick = {
                    onClickLogOut()
                }
            )

        }
    }
}