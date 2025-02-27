package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.remote.response.RecreationSearchResponse
import com.project.giunne.common.data.util.NetworkResult


interface RecreationRepository {
    suspend fun searchRecreation(
        searchQuery: String,
        pageIndex: Int
    ): NetworkResult<RecreationSearchResponse>

    suspend fun createRecreation(
        recreationRequest: RecreationRequest
    ): NetworkResult<RecreationCreateResponse>
}