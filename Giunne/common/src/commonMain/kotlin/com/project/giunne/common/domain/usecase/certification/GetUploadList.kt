package com.project.giunne.common.domain.usecase.certification

import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CertificationRepository

class GetUploadList(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(roadmapId: Long): List<QuestUploadInfo> {
        return certificationRepository
            .getUploadList(roadmapId = roadmapId)
            .successOr(listOf())
    }
}