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
import com.project.giunne.common.presentation.certification.student.state.CertProgress
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.presentation.home.common.BorderButton
import com.project.giunne.common.presentation.home.common.RowWithDropShadow
import com.project.giunne.common.presentation.roadmap.node.NodeStatus
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_next
import com.project.giunne.icon_student_check
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherCheckingBox(
    modifier: Modifier,
    teacherConfirm: NodeStatus,
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

        when (teacherConfirm) {
            NodeStatus.CHECK -> {
                GPAnnotatedText(
                    text = buildAnnotatedString {
                        append("영상을 ")
                        withStyle(
                            style = SpanStyle(
                                color = GPColor.MainOrangeColor,
                                fontSize = 18.gsp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("업로드")
                        }
                        append("하러 갈까요?")
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                BorderButton(
                    modifier = Modifier.wrapContentSize(),
                    title = "올리러가기",
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
            NodeStatus.UPLOAD -> {
                GPAnnotatedText(
                    text = buildAnnotatedString {
                        append("선생님이 영상을\n")
                        withStyle(
                            style = SpanStyle(
                                color = GPColor.MainOrangeColor,
                                fontSize = 18.gsp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("확인중")
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
            else -> {
                GPAnnotatedText(
                    text = buildAnnotatedString {
                        append("선생님이 수업시간에\n")
                        withStyle(
                            style = SpanStyle(
                                color = GPColor.MainOrangeColor,
                                fontSize = 18.gsp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("체크")
                        }
                        append("해줄거에요.")
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}