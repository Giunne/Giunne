package com.project.giunne.common.domain.usecase.shop

import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.ShopRepository

class GetCategoryMapUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(): Map<Long, List<CategoryTypeResponse>> {
        return shopRepository
            .getCategoryMap()
            .successOr(mapOf())
    }
}