package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.service.ShopService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.ShopRepository

class ShopRepositoryImpl(
    private val shopService: ShopService
): ShopRepository {
    override suspend fun getCategoryMap(): NetworkResult<List<GachaResponse>> {
        return handleApi {
            shopService.getCategoryList()
        }.also {

        }
    }
}