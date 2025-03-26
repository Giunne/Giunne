package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.StudentCourseResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetSpecificStudentCourseUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(
        roadmapId: Long,
        playerId: Int
    ): StudentCourseResponse {
        return roadMapRepository
            .getSpecificStudentCourse(
                roadmapId = roadmapId,
                playerId = playerId
            )
            .successOr(StudentCourseResponse())
    }
}