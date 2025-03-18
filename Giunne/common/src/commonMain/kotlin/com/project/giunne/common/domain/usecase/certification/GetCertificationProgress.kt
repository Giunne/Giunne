package com.project.giunne.common.domain.usecase.certification

import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CertificationRepository

class GetCertificationProgress(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(roadmapId: Long): List<StudentQuestInfo> {
        return certificationRepository
            .getCertificationProgress(roadmapId = roadmapId)
            .successOr(listOf())
    }
}