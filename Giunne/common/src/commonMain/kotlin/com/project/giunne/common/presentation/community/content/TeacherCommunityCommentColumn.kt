package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.character_cat_level_1
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.presentation.common.dialog.GPConfirmDialog
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherCommunityCommentColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    commentList: List<CommentInfo>,
    callLike: (Long, () -> Unit) -> Unit,
    callUnlike: (Long, () -> Unit) -> Unit,
    onDeleteButtonClicked: (Long) -> Unit
) {
    var deleteConfirmDialog by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
    ) {
        if (commentList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.gdp),
                state = listState
            ) {
                items(
                    commentList.size
                ) {
                    TeacherCommentItemRow(
                        modifier = Modifier
                            .padding(vertical = 8.gdp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        commentInfo = commentList[it],
                        like = commentList[it].likeCount > 0,
                        onDeleteButtonClicked = {
                            deleteConfirmDialog = true
                            selectedId = commentList[it].id
                        },
                        onLikeButtonClicked = { currentLikeState, onSuccess ->
                            if (currentLikeState) {
                                callUnlike(commentList[it].id.toLong()) { onSuccess() }
                            } else {
                                callLike(commentList[it].id.toLong()) { onSuccess() }
                            }
                        }
                    )
                }
            }
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                state = listState
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(64.gdp),
                        painter = painterResource(Res.drawable.character_cat_level_1),
                        contentDescription = null
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GPText(
                            text = "댓글을 제일 먼저 남겨볼까요?",
                            textSize = 12.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextBlack
                        )
                    }
                }
            }
        }
    }

    with(deleteConfirmDialog) {
        if (this) {
            GPConfirmDialog(
                title = "",
                content = "삭제할까요?",
                onConfirmClicked = {
                    deleteConfirmDialog = false
                    onDeleteButtonClicked(selectedId.toLong())
                    selectedId = 0
                },
                onCancelClicked = {
                    deleteConfirmDialog = false
                    selectedId = 0
                },
            )
        }
    }
}