package com.project.giunne.common.domain.usecase.certification

import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CertificationRepository
import com.project.giunne.common.domain.repository.RoadMapRepository

class GetCertificationHistory(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(roadmapId: Long): List<StudentQuestInfo> {
        return certificationRepository
            .getCertificationHistory(roadmapId = roadmapId)
            .successOr(listOf())
    }
}