package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.response.CommentListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class GetCommentList(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(postId: Long, pageIndex: Int): CommentListResponse {
        return communityRepository
            .getCommentList(postId = postId, pageIndex = pageIndex)
            .successOr(CommentListResponse())
    }
}