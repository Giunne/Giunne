package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.response.PostingListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class GetPostingList(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        roadMapId: Long,
        questName: String,
        nickName: String,
        pageIndex: Int,
        sortDirection: String
    ): PostingListResponse {
        return communityRepository
            .getPostingList(
                roadMapId = roadMapId,
                questName = questName,
                nickName = nickName,
                pageIndex = pageIndex,
                sortDirection = sortDirection,
            )
            .successOr(PostingListResponse())
    }
}