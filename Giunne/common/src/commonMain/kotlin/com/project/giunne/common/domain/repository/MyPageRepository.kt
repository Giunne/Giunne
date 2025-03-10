package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.NetworkResult

interface MyPageRepository {
    suspend fun putInventoryItem(putInventoryItemRequest: PutInventoryItemRequest): NetworkResult<String>
    suspend fun getInventoryItemById(categoryId: Long, pageIndex: Int): NetworkResult<CategoryItemResponse>
}