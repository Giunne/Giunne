package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.NetworkResult

interface CertificationRepository {
    suspend fun getCertificationProgress(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
    suspend fun getCertificationHistory(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
    suspend fun getUploadList(roadmapId: Long): NetworkResult<List<QuestUploadInfo>>
    suspend fun postUploadFile(questId: Long, byteArray: ByteArray, mimeType: String, onProgress: (Long, Long) -> Unit): NetworkResult<String>
    suspend fun postGradeStudent(gradeStudentRequest: GradeStudentRequest): NetworkResult<String>
}