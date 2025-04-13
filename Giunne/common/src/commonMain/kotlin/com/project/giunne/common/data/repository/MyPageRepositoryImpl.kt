package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.AvatarModifyRequest
import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.AvatarInformationResponse
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.service.MyPageService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.MyPageRepository

private const val TAG = "MyPageRepositoryImpl"
class MyPageRepositoryImpl(
    private val myPageService: MyPageService
): MyPageRepository {
    override suspend fun putInventoryItem(putInventoryItemRequest: PutInventoryItemRequest): NetworkResult<String> {
        val response = handleTokenForResponse { myPageService.putInventoryItem(putInventoryItemRequest = putInventoryItemRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getInventoryItemById(
        categoryId: Long,
        pageIndex: Int
    ): NetworkResult<CategoryItemResponse> {
        val response = handleTokenForResponse { myPageService.getInventoryItemById(categoryId, pageIndex) }

        return handleApi(TAG) { response }
    }

    override suspend fun getMyInformation(): NetworkResult<AvatarInformationResponse> {
        val response = handleTokenForResponse { myPageService.getMyInformation() }

        return handleApi(TAG) { response }
    }

    override suspend fun modifyAvatarInformation(avatarModifyRequest: AvatarModifyRequest): NetworkResult<String> {
        val response = handleTokenForResponse { myPageService.modifyAvatarInformation(avatarModifyRequest) }

        return handleApi(TAG) { response }
    }
}