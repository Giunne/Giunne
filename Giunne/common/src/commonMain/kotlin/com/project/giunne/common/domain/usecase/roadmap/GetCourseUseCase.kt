package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetCourseUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(roadmapId: Long): CourseResponse {
        return roadMapRepository
            .getCourse(roadmapId = roadmapId)
            .successOr(CourseResponse())
    }
}