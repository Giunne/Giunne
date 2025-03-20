package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_COMMENT_LIST
import com.project.giunne.common.data.util.DefineUrl.GET_POSTING_DETAIL
import com.project.giunne.common.data.util.DefineUrl.GET_POSTING_DETAIL_LIST
import com.project.giunne.common.data.util.DefineUrl.POST_COMMENT
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface CommunityService {
    @GET(GET_POSTING_DETAIL_LIST)
    suspend fun getPostingDetailList(
        @Query("playerId") playerId: Long,
        @Query("questId") questId: Long
    ): BaseResponse<PostingDetailListResponse>

    @GET("$GET_POSTING_DETAIL/{postId}")
    suspend fun getPostingDetail(
        @Path("postId") postId: Long
    ): BaseResponse<PostingDetailResponse>

    @POST(POST_COMMENT)
    suspend fun postComment(
        @Body commentRequest: CommentRequest
    ): BaseResponse<Long>

    @GET("$GET_COMMENT_LIST/{postId}")
    suspend fun getCommentList(
        @Path("postId") postId: Long,
        @Query("pageIndex") pageIndex: Int,
    ): BaseResponse<CommentListResponse>
}