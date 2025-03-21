package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.remote.response.RecreationListTeacherResponse
import com.project.giunne.common.data.remote.response.RecreationSearchResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_CREATE_RECREATION
import com.project.giunne.common.data.util.DefineUrl.URL_SEARCH_RECREATION
import com.project.giunne.common.data.util.DefineUrl.URL_TEACHER_RECREATION_LIST
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query

interface RecreationService {
    @GET(URL_SEARCH_RECREATION)
    suspend fun searchRecreation(
        @Query("recreationName") searchQuery: String,
        @Query pageIndex: Int
    ): BaseResponse<RecreationSearchResponse>

    @POST(URL_CREATE_RECREATION)
    suspend fun createRecreation(
        @Body recreationRequest: RecreationRequest
    ): BaseResponse<RecreationCreateResponse>

    @GET(URL_TEACHER_RECREATION_LIST)
    suspend fun getTeacherRecreationList(
        @Query pageIndex: Int
    ): BaseResponse<RecreationListTeacherResponse>
}