package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.dummy.roadmapDoneList
import com.project.giunne.common.presentation.certification.student.intent.VideoUploadStore
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.picker.VideoPicker
import com.project.giunne.common.presentation.common.player.VideoWindowPlayer
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.content.CommunityItemRow
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.community.student.dummy.roadmapCommunityList
import com.project.giunne.common.presentation.community.student.dummy.runningCommunityList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.roadcon_3_beast
import org.jetbrains.compose.resources.painterResource

private const val TAG = "RoadMapCertScreen"
@Composable
fun TeacherRoadMapCertScreen(
    modifier: Modifier = Modifier,
    certWaitingList: List<CommunityDto>, //TODO API
    onItemClicked: (CommunityDto) -> Unit
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.gdp, horizontal = 20.gdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GPText(
                text = "인증 요청 내역",
                textSize = 16.gsp,
                textColor = GPColor.TextBlack,
                fontFamily = GPFontFamily.Bold
            )
            SpW(10.gdp)
            Box(
                modifier = Modifier
                    .background(
                        color = GPColor.BackgroundFrameOrange,
                        shape = RoundedCornerShape(12.gdp)
                    )
                    .padding(vertical = 4.gdp, horizontal = 6.gdp),
                contentAlignment = Alignment.Center
            ) {
                GPText(
                    text = certWaitingList.size.toString(),
                    textSize = 10.gsp,
                    textColor = GPColor.White,
                    fontFamily = GPFontFamily.Bold
                )
            }
        }
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.gdp),
                state = scrollState
            ) {
                items(
                    count = certWaitingList.size
                ) {
                    TeacherCertItemRow(
                        name = certWaitingList[it].name,
                        painter = painterResource(certWaitingList[it].character),
                        date = certWaitingList[it].date,
                        rootName = certWaitingList[it].rootName,
                        content = certWaitingList[it].content,
                        onClick = { onItemClicked(certWaitingList[it]) },
                    )
                }
            }
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                state = scrollState
            )
        }
    }
}