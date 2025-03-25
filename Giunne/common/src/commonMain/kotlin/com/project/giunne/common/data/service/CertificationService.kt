package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_CERT_HISTORY
import com.project.giunne.common.data.util.DefineUrl.GET_CERT_PROGRESS
import com.project.giunne.common.data.util.DefineUrl.GET_UPLOAD_LIST
import com.project.giunne.common.data.util.DefineUrl.POST_CERT_STUDENT
import com.project.giunne.common.data.util.DefineUrl.POST_UPLOAD_FILE
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.ReqBuilder
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.MultiPartFormDataContent

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

    @POST(POST_UPLOAD_FILE)
    suspend fun postUploadFile(
        @Query("questId") questId: Long,
        @Body file: MultiPartFormDataContent,
        @ReqBuilder ext: HttpRequestBuilder.() -> Unit
    ): BaseResponse<String>

    @POST(POST_CERT_STUDENT)
    suspend fun postGradeStudent(
        @Body gradeStudentRequest: GradeStudentRequest
    ): BaseResponse<String>
}