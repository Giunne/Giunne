package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.NetworkResult

interface ShopRepository {
    suspend fun getCategoryMap(): NetworkResult<Map<Long, List<CategoryTypeResponse>>>
    suspend fun getCategoryItemById(categoryItemId: Long, pageIndex: Int): NetworkResult<CategoryItemResponse>
    suspend fun getGachaType(): NetworkResult<List<GachaResponse>>
    suspend fun postGacha(gachaRequest: GachaRequest): NetworkResult<Item>
}