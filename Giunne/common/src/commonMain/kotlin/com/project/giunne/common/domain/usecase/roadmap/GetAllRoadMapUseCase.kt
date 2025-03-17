package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetAllRoadMapUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(): List<RoadMapInfo> {
        return roadMapRepository
            .getAllRoadMapList()
            .successOr(listOf())
    }
}