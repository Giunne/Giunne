package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.RecreationListTeacherResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RecreationRepository

class GetRecreationTeacherListUseCase(
    private val recreationRepository: RecreationRepository
) {
    suspend operator fun invoke(
        pageIndex: Int
    ): RecreationListTeacherResponse {
        return recreationRepository
            .getTeacherRecreationList(
                pageIndex = pageIndex
            )
            .successOr(RecreationListTeacherResponse())
    }
}