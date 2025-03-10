package com.project.giunne.common.domain.usecase.shop

import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.ShopRepository

class PostGachaUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(gachaRequest: GachaRequest): Item {
        return shopRepository
            .postGacha(gachaRequest = gachaRequest)
            .successOr(Item())
    }
}