package com.project.giunne.common.domain.usecase.shop

import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.ShopRepository

class GetGachaMapUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(): List<GachaResponse> {
        return shopRepository
            .getCategoryMap()
            .successOr(listOf())
    }
}