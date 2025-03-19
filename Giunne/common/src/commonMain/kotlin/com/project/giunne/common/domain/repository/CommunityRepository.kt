package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface CommunityRepository {
    suspend fun getPostingDetailList(playerId: Long, questId: Long): NetworkResult<PostingDetailListResponse>
    suspend fun getPostingDetail(postId: Long): NetworkResult<PostingDetailResponse>
    suspend fun postComment(commentRequest: CommentRequest): NetworkResult<Long>
    suspend fun getCommentList(postId: Long, lastCommentId: Long, ): NetworkResult<List<CommentInfo>>
}