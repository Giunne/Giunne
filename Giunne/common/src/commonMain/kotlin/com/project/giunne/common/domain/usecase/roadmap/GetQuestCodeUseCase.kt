package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.QuestCodeResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetQuestCodeUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(): QuestCodeResponse {
        return roadMapRepository
            .getQuestCode()
            .successOr(QuestCodeResponse())
    }
}