package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.AvatarModifyRequest
import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.AvatarInformationResponse
import com.project.giunne.common.data.remote.response.CategoryItemResponse
import com.project.giunne.common.data.service.MyPageService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.MyPageRepository

private const val TAG = "MyPageRepositoryImpl"
class MyPageRepositoryImpl(
    private val myPageService: MyPageService
): MyPageRepository {
    override suspend fun putInventoryItem(putInventoryItemRequest: PutInventoryItemRequest): NetworkResult<String> {
        return handleApi(TAG) {
            myPageService.putInventoryItem(putInventoryItemRequest = putInventoryItemRequest)
        }
    }

    override suspend fun getInventoryItemById(
        categoryId: Long,
        pageIndex: Int
    ): NetworkResult<CategoryItemResponse> {
        return handleApi(TAG) {
            myPageService.getInventoryItemById(categoryId, pageIndex)
        }
    }

    override suspend fun getMyInformation(): NetworkResult<AvatarInformationResponse> {
        return handleApi(TAG) {
            myPageService.getMyInformation()
        }
    }

    override suspend fun modifyAvatarInformation(avatarModifyRequest: AvatarModifyRequest): NetworkResult<String> {
        return handleApi(TAG) {
            myPageService.modifyAvatarInformation(avatarModifyRequest)
        }
    }
}