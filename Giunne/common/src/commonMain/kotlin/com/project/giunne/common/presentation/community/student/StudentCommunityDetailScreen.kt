package com.project.giunne.common.presentation.community.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.player.VideoPlayer
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.student.content.CommunityCommentColumn
import com.project.giunne.common.presentation.community.student.content.CommunityDetailInfoRow
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.community.student.dummy.commentTestList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_roadmap
import com.project.giunne.icon_running
import com.project.giunne.image_loader_1
import org.jetbrains.compose.resources.painterResource

@Composable
fun StudentCommunityDetailScreen(
    modifier: Modifier = Modifier,
    communityDto: CommunityDto?,
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (communityDto != null) {
                CommunityDetailInfoRow(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .height(76.gdp),
                    communityDto = communityDto
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
//                    AsyncImage( // TODO API
//                        modifier = Modifier
//                            .clip(shape = RoundedCornerShape(12.gdp))
//                            .fillMaxSize(),
//                        model = "https://picsum.photos/200/300",
//                        placeholder = painterResource(Res.drawable.image_loader_1),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop
//                    )
                    VideoPlayer(
                        videoPath = "",
                        dismiss = {  }
                    )
                }
                SpH(4.gdp)
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.gdp)
                        .height(38.gdp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPText(
                        text = "댓글 " + commentTestList.size.toString(),
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.TextBlack
                    )
                }
                CommunityCommentColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    commentList = commentTestList
                )
            }
        }
    }
}