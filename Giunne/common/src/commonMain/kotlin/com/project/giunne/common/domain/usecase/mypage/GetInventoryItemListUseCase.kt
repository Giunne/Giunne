package com.project.giunne.common.domain.usecase.mypage

import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.MyPageRepository
import com.project.giunne.common.domain.repository.ShopRepository

class GetInventoryItemListUseCase(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(categoryId: Long, pageIndex: Int): CategoryItemResponse {
        return myPageRepository
            .getInventoryItemById(categoryId, pageIndex)
            .successOr(CategoryItemResponse())
    }
}