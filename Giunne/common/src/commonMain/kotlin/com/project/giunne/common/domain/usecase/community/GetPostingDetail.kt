package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class GetPostingDetail(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(postId: Long): PostingDetailResponse {
        return communityRepository
            .getPostingDetail(postId = postId)
            .successOr(PostingDetailResponse())
    }
}