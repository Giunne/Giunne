package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.RecreationStudentJoinResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RecreationRepository

class GetRecreationStudentJoinUseCase(
    private val recreationRepository: RecreationRepository
) {
    suspend operator fun invoke(
        pageIndex: Int
    ): RecreationStudentJoinResponse {
        return recreationRepository
            .getStudentJoinRecreationList(
                pageIndex = pageIndex
            )
            .successOr(RecreationStudentJoinResponse())
    }
}