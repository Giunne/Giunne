package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.remote.response.PostingListResponse
import com.project.giunne.common.data.remote.response.QuestTypeListResponse
import com.project.giunne.common.data.service.CommunityService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
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
        val response = handleTokenForResponse { communityService.getPostingDetailList(playerId = playerId, questId = questId) }

        return handleApi(TAG) { response }
    }

    override suspend fun getPostingDetail(postId: Long): NetworkResult<PostingDetailResponse> {
        val response = handleTokenForResponse { communityService.getPostingDetail(postId = postId) }

        return handleApi(TAG) { response }
    }

    override suspend fun postComment(commentRequest: CommentRequest): NetworkResult<Long> {
        val response = handleTokenForResponse { communityService.postComment(commentRequest = commentRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getCommentList(
        postId: Long,
        pageIndex: Int,
    ): NetworkResult<CommentListResponse> {
        val response = handleTokenForResponse { communityService.getCommentList(postId = postId, pageIndex = pageIndex) }

        return handleApi(TAG) { response }
    }

    override suspend fun deleteComment(postId: Long): NetworkResult<String> {
        val response = handleTokenForResponse { communityService.deleteComment(postId = postId) }

        return handleApi(TAG) { response }
    }

    override suspend fun postCommentLike(commentLikeRequest: CommentLikeRequest): NetworkResult<String> {
        val response = handleTokenForResponse { communityService.postCommentLike(commentLikeRequest = commentLikeRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun postCommentUnlike(commentLikeRequest: CommentLikeRequest): NetworkResult<String> {
        val response = handleTokenForResponse { communityService.postCommentUnlike(commentLikeRequest = commentLikeRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getPostingList(
        roadMapId: Long,
        questName: String,
        nickName: String,
        pageIndex: Int,
        sortDirection: String
    ): NetworkResult<PostingListResponse> {
        val response = handleTokenForResponse {
            communityService.getPostingList(
                roadMapId = roadMapId,
                questName = questName,
                nickName = nickName,
                pageIndex = pageIndex,
                sortDirection = sortDirection,
            )
        }

        return handleApi(TAG) { response }
    }

    override suspend fun getQuestTypeList(
        roadmapId: Long,
        pageIndex: Int
    ): NetworkResult<QuestTypeListResponse> {
        val response = handleTokenForResponse {
            communityService.getQuestTypeList(
                roadmapId = roadmapId,
                pageIndex = pageIndex,
            )
        }

        return handleApi(TAG) { response }
    }
}