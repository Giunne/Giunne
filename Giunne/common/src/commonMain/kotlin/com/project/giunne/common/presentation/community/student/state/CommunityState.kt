package com.project.giunne.common.presentation.community.student.state

import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.DataThrowable

data class CommunityState(
    val postingDetailInfo: PostingDetailResponse = PostingDetailResponse(),
    val postingDetailListInfo: PostingDetailListResponse = PostingDetailListResponse(),

    val loading: Boolean = false,
    val error: DataThrowable? = null
)
