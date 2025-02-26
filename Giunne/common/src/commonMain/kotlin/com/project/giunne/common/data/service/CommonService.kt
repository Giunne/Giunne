package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.response.SchoolListResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_SCHOOL_LIST
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query

interface CommonService {
    @GET(URL_SCHOOL_LIST)
    suspend fun getSchoolList(
        @Query("schoolNm") schoolNm: String,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortProperty") sortProperty: String,
        @Query("sortDirection") sortDirection: String,
    ): BaseResponse<SchoolListResponse>
}