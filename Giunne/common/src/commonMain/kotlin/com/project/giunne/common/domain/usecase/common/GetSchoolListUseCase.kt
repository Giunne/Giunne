package com.project.giunne.common.domain.usecase.common

import com.project.giunne.common.data.remote.request.PageNationRequest
import com.project.giunne.common.data.remote.response.SchoolListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommonRepository

class GetSchoolListUseCase(
    private val commonRepository: CommonRepository
) {
    suspend operator fun invoke(
        schoolNm: String,
        pageNationRequest: PageNationRequest
    ): SchoolListResponse {
        return commonRepository
            .getSchoolList(
                schoolNm = schoolNm,
                pageNationRequest = pageNationRequest,
            )
            .successOr(SchoolListResponse())
    }
}