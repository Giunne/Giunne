package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.remote.response.PossibleItemCountInto
import com.project.giunne.common.data.service.ShopService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.ShopRepository
import com.project.giunne.common.util.GLog

private const val TAG = "ShopRepositoryImpl"
class ShopRepositoryImpl(
    private val shopService: ShopService
): ShopRepository {
    override suspend fun getCategoryMap(): NetworkResult<Map<Long, List<CategoryTypeResponse>>> {
        return handleApi(TAG) {
            shopService.getCategoryMap()
        }
    }

    override suspend fun getCategoryItemById(categoryItemId: Long, pageIndex: Int): NetworkResult<CategoryItemResponse> {
        return handleApi(TAG) {
            shopService.getCategoryItemById(categoryItemId, pageIndex)
        }
    }

    override suspend fun getGachaType(): NetworkResult<List<GachaResponse>> {
        return handleApi(TAG) {
            GLog.d(TAG, "getGachaType Request => ")
            shopService.getGachaType()
        }
    }

    override suspend fun postGacha(gachaRequest: GachaRequest): NetworkResult<Item> {
        return handleApi(TAG) {
            GLog.d(TAG, "postGacha Request => $gachaRequest")
            shopService.postGacha(gachaRequest = gachaRequest)
        }
    }

    override suspend fun getPossibleItemCount(gachaTypes: String): NetworkResult<PossibleItemCountInto> {
        return handleApi(TAG) {
            shopService.getPossibleItemCount(gachaTypes = gachaTypes)
        }
    }
}