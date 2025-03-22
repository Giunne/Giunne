package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.community.student.dummy.CommentDto
import com.project.giunne.common.util.gdp

@Composable
fun StudentCommunityCommentColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    commentList: List<CommentInfo>
) {
    /////TEST///// TODO API
    var deleteConfirmDialog by remember { mutableStateOf(false) }
    //////////////

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.gdp),
            state = listState
        ) {
            items(
                commentList.size
            ) {
                StudentCommentItemRow(
                    modifier = Modifier
                        .padding(vertical = 8.gdp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    commentInfo = commentList[it],
                    onDeleteButtonClicked = { deleteConfirmDialog = true }, // TODO 동작
                )
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            state = listState
        )
    }

    with(deleteConfirmDialog) {
        if (this) {
            GPConfirmDialog(
                title = "",
                content = "삭제할까요?",
                onConfirmClicked = {
                    deleteConfirmDialog = false
                }, //TODO API
                onCancelClicked = { deleteConfirmDialog = false },
            )
        }
    }
}