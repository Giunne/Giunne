package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.PageNationRequest
import com.project.giunne.common.data.remote.response.SchoolListResponse
import com.project.giunne.common.data.util.NetworkResult

interface CommonRepository {
    suspend fun getSchoolList(
        schoolNm: String,
        pageNationRequest: PageNationRequest
    ): NetworkResult<SchoolListResponse>
}