package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.request.QuestStateRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class ModifyQuestStateUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(
        questStateRequest: QuestStateRequest
    ): String {
        return roadMapRepository
            .modifyQuestState(questStateRequest = questStateRequest)
            .successOr("")
    }
}