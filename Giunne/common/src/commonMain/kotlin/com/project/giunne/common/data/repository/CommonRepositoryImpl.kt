package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.PageNationRequest
import com.project.giunne.common.data.remote.response.SchoolListResponse
import com.project.giunne.common.data.service.CommonService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.CommonRepository
import com.project.giunne.common.util.GLog

private const val TAG = "CommonRepositoryImpl"
class CommonRepositoryImpl(
    private val commonService: CommonService
): CommonRepository {
    override suspend fun getSchoolList(
        schoolNm: String,
        pageNationRequest: PageNationRequest
    ): NetworkResult<SchoolListResponse> {
        return handleApi(TAG) {
            commonService.getSchoolList(
                schoolNm = schoolNm,
                pageIndex = pageNationRequest.pageIndex,
                pageSize = pageNationRequest.pageSize,
                sortProperty = pageNationRequest.sortProperty,
                sortDirection = pageNationRequest.sortDirection,
            ).also {
                GLog.d(TAG, "response => ${it.value}")
            }
        }
    }
}