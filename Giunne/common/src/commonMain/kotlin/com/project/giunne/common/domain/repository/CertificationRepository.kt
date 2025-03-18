package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.NetworkResult

interface CertificationRepository {
    suspend fun getCertificationProgress(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
    suspend fun getCertificationHistory(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
}