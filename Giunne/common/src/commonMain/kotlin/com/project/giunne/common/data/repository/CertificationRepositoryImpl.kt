package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.service.CertificationService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.CertificationRepository

private const val TAG = "CertificationRepositoryImpl"
class CertificationRepositoryImpl(
    private val certificationService: CertificationService
): CertificationRepository {
    override suspend fun getCertificationProgress(roadmapId: Long): NetworkResult<List<StudentQuestInfo>> {
        return handleApi(TAG) {
            certificationService.getCertificationProgress(roadmapId = roadmapId)
        }
    }

    override suspend fun getCertificationHistory(roadmapId: Long): NetworkResult<List<StudentQuestInfo>> {
        return handleApi(TAG) {
            certificationService.getCertificationHistory(roadmapId = roadmapId)
        }
    }

    override suspend fun getUploadList(roadmapId: Long): NetworkResult<List<QuestUploadInfo>> {
        return handleApi(TAG) {
            certificationService.getUploadList(roadmapId = roadmapId)
        }
    }
}