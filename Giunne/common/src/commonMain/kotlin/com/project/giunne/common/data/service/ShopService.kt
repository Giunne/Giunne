package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_CATEGORY_ITEM_BY_ID
import com.project.giunne.common.data.util.DefineUrl.GET_CATEGORY_ROOT
import com.project.giunne.common.data.util.DefineUrl.GET_GACHA_TYPE
import com.project.giunne.common.data.util.DefineUrl.POST_GACHA
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query


interface ShopService {
    @GET(GET_CATEGORY_ROOT)
    suspend fun getCategoryMap(): BaseResponse<Map<Long, List<CategoryTypeResponse>>>

    @GET(GET_CATEGORY_ITEM_BY_ID)
    suspend fun getCategoryItemById(
        @Query("categoryId") categoryId: Long,
        @Query("pageIndex") pageIndex: Int
    ): BaseResponse<CategoryItemResponse>

    @GET(GET_GACHA_TYPE)
    suspend fun getGachaType(): BaseResponse<List<GachaResponse>>

    @POST(POST_GACHA)
    suspend fun postGacha(
        @Body gachaRequest: GachaRequest,
    ): BaseResponse<Item>
}