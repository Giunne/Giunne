package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.NetworkResult

interface CommunityRepository {
    suspend fun getPostingDetailList(playerId: Long, questId: Long): NetworkResult<PostingDetailListResponse>
    suspend fun getPostingDetail(postId: Long): NetworkResult<PostingDetailResponse>
    suspend fun postComment(commentRequest: CommentRequest): NetworkResult<Long>
    suspend fun getCommentList(postId: Long, pageIndex: Int): NetworkResult<CommentListResponse>
}