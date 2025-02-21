package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.util.BaseResponse
import de.jensklingenberg.ktorfit.http.GET


interface ShopService {
    @GET("items/order/gacha-type")
    suspend fun getCategoryList(): BaseResponse<List<GachaResponse>>
}