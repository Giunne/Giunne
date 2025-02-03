package com.project.giunne.common.presentation.main.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.main.dummy.Noti
import com.project.giunne.common.presentation.main.dummy.NotiType
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_badge
import com.project.giunne.icon_person
import org.jetbrains.compose.resources.painterResource

private const val TAG = "NotificationScreen"
@Composable
internal fun NotificationScreen(
    modifier: Modifier = Modifier,
    notificationItemList: List<Noti>,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GPColor.BackgroundLightGray),
        ) {
            items(
                count = notificationItemList.size
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(66.gdp)
                        .padding(vertical = 8.gdp, horizontal = 16.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPSquircleShape(
                        modifier = Modifier.size(24.gdp),
                        backgroundColor = when (notificationItemList[it].type) {
                            NotiType.LoadMap, NotiType.Running -> {
                                GPColor.BackgroundFrameOrange
                            }
                            NotiType.Comment -> {
                                GPColor.ButtonBlack
                            }
                        },
                        content = {
                            when (notificationItemList[it].type) {
                                NotiType.LoadMap, NotiType.Running -> {
                                    Image(
                                        modifier = Modifier.size(16.gdp),
                                        painter = painterResource(Res.drawable.icon_badge),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(GPColor.White)
                                    )
                                }
                                NotiType.Comment -> {
                                    Image(
                                        modifier = Modifier.size(12.gdp),
                                        painter = painterResource(Res.drawable.icon_person),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(GPColor.White)
                                    )
                                }
                            }
                        }
                    )
                    SpW(16.gdp)
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        GPText(
                            text = when (notificationItemList[it].type) {
                                NotiType.LoadMap -> {
                                    "로드맵 인증"
                                }
                                NotiType.Comment -> {
                                    "댓글작성"
                                }
                                NotiType.Running -> {
                                    "러닝 인증"
                                }
                            },
                            textSize = 10.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.ButtonLightGray
                        )
                        SpH(6.gdp)
                        GPText(
                            text = notificationItemList[it].content,
                            textSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.TextBlack
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(20.gdp)
                            .align(Alignment.Top)
                    ){
                        GPText(
                            modifier = Modifier.align(Alignment.BottomEnd),
                            text = notificationItemList[it].time,
                            textSize = 10.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.ButtonLightGray
                        )
                        if (!notificationItemList[it].isRead) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(6.gdp)
                                    .background(GPColor.BackgroundFrameOrange, CircleShape),
                            )
                        }
                    }
                }
            }
        }
    }
}