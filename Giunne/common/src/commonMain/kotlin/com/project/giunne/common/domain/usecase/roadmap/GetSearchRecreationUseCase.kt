package com.project.giunne.common.domain.usecase.roadmap

import com.project.giunne.common.data.remote.response.RecreationSearchResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.RecreationRepository

class GetSearchRecreationUseCase(
    private val recreationRepository: RecreationRepository
) {
    suspend operator fun invoke(
        searchQuery: String,
        pageIndex: Int = 1
    ): RecreationSearchResponse {
        return recreationRepository
            .searchRecreation(
                searchQuery = searchQuery,
                pageIndex = pageIndex
            )
            .successOr(RecreationSearchResponse())
    }
}