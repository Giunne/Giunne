package com.project.giunne.common.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPBackButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.presentation.main.dummy.Noti
import com.project.giunne.common.presentation.notification.content.NotificationItem
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_rotate
import com.project.giunne.icon_write
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    notificationItemList: List<Noti>
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .addFocusCleaner(focusManager),
        topBar = {
            GPMainTopBar(
                titleText = "알림",
                leftIcon = {
                    GPBackButton { onBackButtonClicked() }
                },
                rightIcon = {
                    GPIconButton(
                        modifier = Modifier
                            .padding(end = 16.gdp)
                            .size(32.gdp),
                        icon = {
                            Image(
                                modifier = Modifier.size(16.gdp),
                                painter = painterResource(Res.drawable.icon_write),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(GPColor.White)
                            )
                        },
                        normalColor = GPColor.ButtonBlack,
                        pressColor = GPColor.ButtonPressBlack,
                        hoverColor = GPColor.ButtonHoverBlack,
                        onClick = {
                        },
                        shadow = false
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GPColor.BackgroundLightGray),
        ) {
            items(
                count = notificationItemList.size
            ) {
                NotificationItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(66.gdp)
                        .padding(vertical = 8.gdp, horizontal = 16.gdp),
                    notificationInfo = notificationItemList[it]
                )
            }
        }
    }
}