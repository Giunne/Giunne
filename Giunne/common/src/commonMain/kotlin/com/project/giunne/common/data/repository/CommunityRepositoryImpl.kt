package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.service.CommunityService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.CommunityRepository

private const val TAG = "CommunityRepositoryImpl"
class CommunityRepositoryImpl(
    private val communityService: CommunityService
): CommunityRepository {
    override suspend fun getPostingDetailList(
        playerId: Long,
        questId: Long
    ): NetworkResult<PostingDetailListResponse> {
        return handleApi(TAG) {
            communityService.getPostingDetailList(playerId = playerId, questId = questId)
        }
    }

    override suspend fun getPostingDetail(postId: Long): NetworkResult<PostingDetailResponse> {
        return handleApi(TAG) {
            communityService.getPostingDetail(postId = postId)
        }
    }

    override suspend fun postComment(commentRequest: CommentRequest): NetworkResult<Long> {
        return handleApi(TAG) {
            communityService.postComment(commentRequest = commentRequest)
        }
    }

    override suspend fun getCommentList(
        postId: Long,
        pageIndex: Int,
    ): NetworkResult<CommentListResponse> {
        return handleApi(TAG) {
            communityService.getCommentList(postId = postId, pageIndex = pageIndex)
        }
    }

    override suspend fun postCommentLike(commentLikeRequest: CommentLikeRequest): NetworkResult<String> {
        return handleApi(TAG) {
            communityService.postCommentLike(commentLikeRequest = commentLikeRequest)
        }
    }

    override suspend fun postCommentUnlike(commentLikeRequest: CommentLikeRequest): NetworkResult<String> {
        return handleApi(TAG) {
            communityService.postCommentUnlike(commentLikeRequest = commentLikeRequest)
        }
    }
}