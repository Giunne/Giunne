package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class ModifyQuestInfoUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(modifyQuestInfoRequest: ModifyQuestInfoRequest): QuestInfo {
        return roadMapRepository
            .modifyQuestInfo(modifyQuestInfoRequest = modifyQuestInfoRequest)
            .successOr(QuestInfo())
    }
}