package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.GET_CATEGORY_ITEM_BY_ID
import com.project.giunne.common.data.util.DefineUrl.GET_INVENTORY_ITEM_BY_ID
import com.project.giunne.common.data.util.DefineUrl.PUT_INVENTORY_ITEM
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Query

interface MyPageService {
    @PUT(PUT_INVENTORY_ITEM)
    suspend fun putInventoryItem(
        @Body putInventoryItemRequest: PutInventoryItemRequest
    ): BaseResponse<String>

    @GET(GET_INVENTORY_ITEM_BY_ID)
    suspend fun getInventoryItemById(
        @Query("categoryId") categoryId: Long,
        @Query("pageIndex") pageIndex: Int
    ): BaseResponse<CategoryItemResponse>
}