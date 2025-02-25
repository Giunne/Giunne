package com.project.giunne.common.domain.usecase.shop

import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.ShopRepository

class GetCategoryItemListUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(categoryId: Long, pageIndex: Int): CategoryItemResponse {
        return shopRepository
            .getCategoryItemById(categoryId, pageIndex)
            .successOr(CategoryItemResponse())
    }
}