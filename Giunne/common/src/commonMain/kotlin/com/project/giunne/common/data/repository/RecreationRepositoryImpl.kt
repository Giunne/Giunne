package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.remote.response.RecreationListTeacherResponse
import com.project.giunne.common.data.remote.response.RecreationSearchResponse
import com.project.giunne.common.data.service.RecreationService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.RecreationRepository

private const val TAG = "RecreationRepositoryImpl"
class RecreationRepositoryImpl(
    private val service: RecreationService
): RecreationRepository {
    override suspend fun searchRecreation(
        searchQuery: String,
        pageIndex: Int
    ): NetworkResult<RecreationSearchResponse> {
        return handleApi(TAG) {
            handleTokenForResponse {
                service.searchRecreation(
                    searchQuery = searchQuery,
                    pageIndex = pageIndex
                )
            }
        }
    }

    override suspend fun createRecreation(recreationRequest: RecreationRequest): NetworkResult<RecreationCreateResponse> {
        return handleApi(TAG) {
            handleTokenForResponse {
                service.createRecreation(
                    recreationRequest = recreationRequest
                )
            }
        }
    }

    override suspend fun getTeacherRecreationList(pageIndex: Int): NetworkResult<RecreationListTeacherResponse> {
        return handleApi(TAG) {
            handleTokenForResponse {
                service.getTeacherRecreationList(
                    pageIndex = pageIndex
                )
            }
        }
    }
}