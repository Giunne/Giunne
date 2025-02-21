package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.util.NetworkResult

interface ShopRepository {
    suspend fun getCategoryMap(): NetworkResult<List<GachaResponse>>
}