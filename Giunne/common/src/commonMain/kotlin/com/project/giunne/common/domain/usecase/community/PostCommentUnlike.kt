package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class PostCommentUnlike(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(commentLikeRequest: CommentLikeRequest): String {
        return communityRepository
            .postCommentUnlike(commentLikeRequest = commentLikeRequest)
            .successOr("")
    }
}