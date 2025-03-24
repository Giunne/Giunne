package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class GetPostingDetailList(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(playerId: Long, questId: Long): PostingDetailListResponse {
        return communityRepository
            .getPostingDetailList(playerId = playerId, questId = questId)
            .successOr(PostingDetailListResponse())
    }
}