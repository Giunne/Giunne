package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_CERT_HISTORY
import com.project.giunne.common.data.util.DefineUrl.GET_CERT_PROGRESS
import com.project.giunne.common.data.util.DefineUrl.GET_POSTING_DETAIL
import com.project.giunne.common.data.util.DefineUrl.GET_POSTING_DETAIL_LIST
import com.project.giunne.common.data.util.DefineUrl.GET_UPLOAD_LIST
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface CertificationService {
    @GET(GET_CERT_PROGRESS)
    suspend fun getCertificationProgress(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<List<StudentQuestInfo>>

    @GET(GET_CERT_HISTORY)
    suspend fun getCertificationHistory(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<List<StudentQuestInfo>>

    @GET(GET_UPLOAD_LIST)
    suspend fun getUploadList(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<List<QuestUploadInfo>>
}