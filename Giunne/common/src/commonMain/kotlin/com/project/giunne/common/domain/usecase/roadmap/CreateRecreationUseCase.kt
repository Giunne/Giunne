package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RecreationRepository

class CreateRecreationUseCase(
    private val recreationRepository: RecreationRepository
) {
    suspend operator fun invoke(
        recreationRequest: RecreationRequest
    ): RecreationCreateResponse {
        return recreationRepository
            .createRecreation(recreationRequest)
            .successOr(RecreationCreateResponse())
    }
}