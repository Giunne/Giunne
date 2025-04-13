package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.remote.response.PossibleItemCountInto
import com.project.giunne.common.data.service.ShopService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.ShopRepository
import com.project.giunne.common.util.GLog

private const val TAG = "ShopRepositoryImpl"
class ShopRepositoryImpl(
    private val shopService: ShopService
): ShopRepository {
    override suspend fun getCategoryMap(): NetworkResult<Map<Long, List<CategoryTypeResponse>>> {
        val response = handleTokenForResponse { shopService.getCategoryMap() }

        return handleApi(TAG) { response }
    }

    override suspend fun getCategoryItemById(categoryItemId: Long, pageIndex: Int): NetworkResult<CategoryItemResponse> {
        val response = handleTokenForResponse { shopService.getCategoryItemById(categoryItemId, pageIndex) }

        return handleApi(TAG) { response }
    }

    override suspend fun getGachaType(): NetworkResult<List<GachaResponse>> {
        val response = handleTokenForResponse { shopService.getGachaType() }

        return handleApi(TAG) { response }
    }

    override suspend fun postGacha(gachaRequest: GachaRequest): NetworkResult<Item> {
        val response = handleTokenForResponse {
            handleTokenForResponse {
                GLog.d(TAG, "postGacha Request => $gachaRequest")
                shopService.postGacha(gachaRequest = gachaRequest)
            }
        }

        return handleApi(TAG) { response }
    }

    override suspend fun getPossibleItemCount(gachaTypes: String): NetworkResult<PossibleItemCountInto> {
        val response = handleTokenForResponse { shopService.getPossibleItemCount(gachaTypes = gachaTypes) }

        return handleApi(TAG) { response }
    }
}