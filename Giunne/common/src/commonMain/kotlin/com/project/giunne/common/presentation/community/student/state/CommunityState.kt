package com.project.giunne.common.presentation.community.student.state

import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.DataThrowable

data class CommunityState(
    val postingDetailInfo: PostingDetailResponse = PostingDetailResponse(),
    val commentList: List<CommentInfo> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),

    val loading: Boolean = false,
    val error: DataThrowable? = null
)
