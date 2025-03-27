package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.remote.response.PostingListResponse
import com.project.giunne.common.data.remote.response.QuestTypeListResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_POSTING_LIST
import com.project.giunne.common.data.util.DefineUrl.GET_QUEST_TYPE
import com.project.giunne.common.data.util.DefineUrl.POST_COMMENT_LIKE
import com.project.giunne.common.data.util.DefineUrl.POST_COMMENT_UNLIKE
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface CommunityRepository {
    suspend fun getPostingDetailList(playerId: Long, questId: Long): NetworkResult<PostingDetailListResponse>
    suspend fun getPostingDetail(postId: Long): NetworkResult<PostingDetailResponse>
    suspend fun postComment(commentRequest: CommentRequest): NetworkResult<Long>
    suspend fun getCommentList(postId: Long, pageIndex: Int): NetworkResult<CommentListResponse>
    suspend fun deleteComment(postId: Long, ): NetworkResult<String>
    suspend fun postCommentLike(commentLikeRequest: CommentLikeRequest): NetworkResult<String>
    suspend fun postCommentUnlike(commentLikeRequest: CommentLikeRequest): NetworkResult<String>
    suspend fun getPostingList(roadMapId: Long, questName: String, nickName: String, pageIndex: Int, sortDirection: String, ): NetworkResult<PostingListResponse>
    suspend fun getQuestTypeList(roadmapId: Long, pageIndex: Int, ): NetworkResult<QuestTypeListResponse>
}