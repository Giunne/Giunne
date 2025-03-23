package com.project.giunne.common.domain.usecase.certification

import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CertificationRepository

class PostGradeStudentUseCase(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(
        gradeStudentRequest: GradeStudentRequest
    ) {
        certificationRepository
            .postGradeStudent(gradeStudentRequest = gradeStudentRequest)
            .successOr("")
    }
}