package com.project.giunne.common.presentation.friend.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_dropdown_expanded
import com.project.giunne.icon_vertical_more
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherFriendItemRow(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    isPointModifyCheck: Boolean,
    friendInfo: AvatarUserResponse,
    onClick: (Int) -> Unit,
    onPointModifyButtonClicked: () -> Unit,
    onExpModifyButtonClicked: () -> Unit,
    onResetPasswordButtonClicked: () -> Unit
) {

    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    var isExpended by remember { mutableStateOf(false) }

    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed -> GPColor.ButtonPressWhite
            isHovered -> GPColor.ButtonHoverWhite
            else -> GPColor.White
        },
    )

    val animatedHeight by animateDpAsState(
        targetValue = when(isExpended) {
            true -> 38.gdp
            false -> 0.gdp
        }
    )

    val animatedRotate by animateFloatAsState(
        targetValue = when(isExpended) {
            true -> 180f
            false -> 0f
        }
    )

    Column (
        modifier = modifier
            .background(
                color = fillColor,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(8.gdp)
            .noRippleClickable(interactionSource = interactionSource) {
//                if (!isPointModifyCheck) {
                    onClick(friendInfo.id)
//                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GPSquircleShape(
                modifier = Modifier
                    .height(48.gdp)
                    .aspectRatio(1f),
                backgroundColor = GPColor.BackgroundFrameOrange,
                content = {
                    GPSmallCharacter(
                        modifier = Modifier.fillMaxSize(),
                        wearingItems = friendInfo.wearingItems
                    )
                }
            )
            SpW(16.gdp)
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPText(
                    modifier = Modifier.weight(1f),
                    text = friendInfo.nickname,
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextBlack
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    GPText(
                        text = friendInfo.level.toString(),
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.MainOrangeColor
                    )
                    GPText(
                        text = " 레벨",
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.TextBlack
                    )
                }
                SpH(2.gdp)
                Row {
                    GPText(
                        text = friendInfo.exp.toString(),
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.MainOrangeColor
                    )
                    GPText(
                        text = " exp",
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.TextBlack
                    )
                }
                SpH(2.gdp)
                Row {
                    GPText(
                        text = friendInfo.point.toString(),
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.MainOrangeColor
                    )
                    GPText(
                        text = " 코인",
                        textSize = 12.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.TextBlack
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .height(animatedHeight),
            verticalAlignment = Alignment.Bottom
        ) {
            GPButton(
                modifier = Modifier
                    .height(32.gdp),
                normalColor = GPColor.ButtonLightGray,
                pressColor = GPColor.ButtonPressLightGray,
                hoverColor = GPColor.ButtonHoverLightGray,
                horizontalPadding = 8.gdp,
                onClick = { onResetPasswordButtonClicked() },
            ) {
                GPText(
                    text = "비밀번호 초기화",
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GPButton(
                modifier = Modifier
                    .height(32.gdp),
                normalColor = GPColor.ButtonBlack,
                pressColor = GPColor.ButtonPressBlack,
                hoverColor = GPColor.ButtonHoverBlack,
                horizontalPadding = 8.gdp,
                onClick = { onPointModifyButtonClicked() },
            ) {
                GPText(
                    text = "코인 수정",
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
            SpW(8.gdp)
            GPButton(
                modifier = Modifier
                    .height(32.gdp),
                normalColor = GPColor.ButtonBlack,
                pressColor = GPColor.ButtonPressBlack,
                hoverColor = GPColor.ButtonHoverBlack,
                horizontalPadding = 8.gdp,
                onClick = { onExpModifyButtonClicked() },
            ) {
                GPText(
                    text = "경험치 지급",
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }

        Box(
            modifier = Modifier
                .height(8.gdp)
                .fillMaxWidth()
                .noRippleClickable { isExpended = !isExpended },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .width(16.gdp)
                    .height(6.gdp)
                    .rotate(animatedRotate),
                painter = painterResource(Res.drawable.icon_dropdown_expanded),
                contentDescription = null,
                colorFilter = ColorFilter.tint(GPColor.ButtonLightGray),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}