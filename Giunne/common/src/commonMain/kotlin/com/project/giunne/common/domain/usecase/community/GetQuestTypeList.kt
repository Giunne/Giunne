package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.remote.response.QuestTypeListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class GetQuestTypeList(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(
        roadmapId: Long,
        pageIndex: Int
    ): QuestTypeListResponse {
        return communityRepository
            .getQuestTypeList(
                roadmapId = roadmapId,
                pageIndex = pageIndex,
            )
            .successOr(QuestTypeListResponse())
    }
}