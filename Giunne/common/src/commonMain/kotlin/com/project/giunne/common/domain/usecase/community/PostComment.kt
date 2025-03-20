package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.response.CommentInfo
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class PostComment(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(commentRequest: CommentRequest): Long {
        return communityRepository
            .postComment(commentRequest = commentRequest)
            .successOr(-1)
    }
}