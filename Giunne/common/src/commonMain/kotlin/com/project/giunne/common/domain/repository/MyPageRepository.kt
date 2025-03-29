package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.AvatarModifyRequest
import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.AvatarInformationResponse
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.NetworkResult

interface MyPageRepository {
    suspend fun putInventoryItem(putInventoryItemRequest: PutInventoryItemRequest): NetworkResult<String>
    suspend fun getInventoryItemById(categoryId: Long, pageIndex: Int): NetworkResult<CategoryItemResponse>
    suspend fun getMyInformation(): NetworkResult<AvatarInformationResponse>
    suspend fun modifyAvatarInformation(avatarModifyRequest: AvatarModifyRequest): NetworkResult<String>
}