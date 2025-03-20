package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_UPLOAD_LIST
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface CertificationRepository {
    suspend fun getCertificationProgress(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
    suspend fun getCertificationHistory(roadmapId: Long): NetworkResult<List<StudentQuestInfo>>
    suspend fun getUploadList(roadmapId: Long): NetworkResult<List<QuestUploadInfo>>
}