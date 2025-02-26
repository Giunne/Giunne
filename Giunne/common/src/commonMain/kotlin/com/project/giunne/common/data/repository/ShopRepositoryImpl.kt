package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.service.ShopService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.ShopRepository

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
            shopService.getGachaType()
        }
    }
}