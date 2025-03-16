package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.StudentCourseResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetStudentCourseUseCase(
    private val roadMapRepository: RoadMapRepository
) {
    suspend operator fun invoke(roadmapId: Long): StudentCourseResponse {
        return roadMapRepository
            .getStudentCourse(roadmapId = roadmapId)
            .successOr(StudentCourseResponse())
    }
}