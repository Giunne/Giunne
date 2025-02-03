package com.project.giunne.common.presentation.community.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.content.PageSelectRow
import com.project.giunne.common.presentation.certification.student.content.RoadMapCertScreen
import com.project.giunne.common.presentation.certification.student.content.RoadmapCertConfirmDialog
import com.project.giunne.common.presentation.certification.student.content.RunningCertConfirmDialog
import com.project.giunne.common.presentation.certification.student.content.RunningCertScreen
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.student.content.EmptyBox
import com.project.giunne.common.presentation.community.student.content.SearchRow
import com.project.giunne.common.presentation.friend.dummy.friendList
import com.project.giunne.common.presentation.friend.student.StudentFriendComponent
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_arrow_right
import com.project.giunne.icon_back_arrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

private const val TAG = "StudentCommunityScreen"
@Composable
internal fun StudentCommunityScreen(
    component: StudentCommunityComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    ///// test /////
    var page by remember { mutableStateOf(CertPage.RoadMap) }

    var loading by remember { mutableStateOf(false) }
    ////////////////

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize()
                .padding(horizontal = 16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.gdp)
            )
            EmptyBox()
        }
    }

    if (loading) {
        Loader()
    }
}