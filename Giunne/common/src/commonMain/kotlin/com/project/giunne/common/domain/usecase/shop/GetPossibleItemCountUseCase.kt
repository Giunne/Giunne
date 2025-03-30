package com.project.giunne.common.domain.usecase.shop

import com.project.giunne.common.data.remote.response.PossibleItemCountInto
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.ShopRepository

class GetPossibleItemCountUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(gachaTypes: String): PossibleItemCountInto {
        return shopRepository
            .getPossibleItemCount(gachaTypes = gachaTypes)
            .successOr(PossibleItemCountInto())
    }
}