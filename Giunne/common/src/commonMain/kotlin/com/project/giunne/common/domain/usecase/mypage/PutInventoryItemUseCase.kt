package com.project.giunne.common.domain.usecase.mypage

import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.MyPageRepository
import com.project.giunne.common.domain.repository.ShopRepository

class PutInventoryItemUseCase(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(putInventoryItemRequest: PutInventoryItemRequest): String {
        return myPageRepository
            .putInventoryItem(putInventoryItemRequest = putInventoryItemRequest)
            .successOr("")
    }
}