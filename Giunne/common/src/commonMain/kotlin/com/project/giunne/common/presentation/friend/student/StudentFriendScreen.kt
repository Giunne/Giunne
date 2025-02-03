package com.project.giunne.common.presentation.friend.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.friend.dummy.friendList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

private const val TAG = "StudentFriendScreen"
@Composable
internal fun StudentFriendScreen(
    component: StudentFriendComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GPColor.BackgroundLightGray)
        ) {
            items(
                count = friendList.size
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.gdp)
                        .padding(horizontal = 16.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPSquircleShape(
                        modifier = Modifier.size(52.gdp),
                        backgroundColor = GPColor.BackgroundFrameOrange,
                        content = {
                            Image(
                                painter = painterResource(friendList[it].avatar.img),
                                contentDescription = null
                            )
                        }
                    )
                    SpW(16.gdp)
                    GPText(
                        modifier = Modifier.weight(1f),
                        text = friendList[it].name,
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Medium,
                        textColor = GPColor.TextBlack
                    )
                    Row{
                        GPText(
                            text = "레벨 ",
                            textSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.TextBlack
                        )
                        GPText(
                            text = friendList[it].level.toString(),
                            textSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium,
                            textColor = GPColor.MainOrangeColor
                        )
                    }
                }
            }
        }
    }
}