package com.project.giunne.common.presentation.community.student.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.community.student.dummy.CommentDto
import com.project.giunne.common.util.gdp

@Composable
fun TeacherCommunityCommentColumn(
    modifier: Modifier = Modifier,
    commentList: List<CommentDto>
) {
    val scrollState = rememberLazyListState()

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.gdp),
            state = scrollState
        ) {
            items(
                commentList.size
            ) {
                TeacherCommentItemRow(
                    modifier = Modifier
                        .padding(vertical = 8.gdp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    commentDto = commentList[it],
                    like = commentList[it].like,
                    onMenuButtonClicked = {  },
                    onLikeButtonClicked = { like ->
                        commentList[it].like = !like
                    }
                )
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            state = scrollState
        )
    }
}