package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.service.AvatarService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.AvatarRepository

private const val TAG = "AvatarRepositoryImpl"
class AvatarRepositoryImpl(
    private val avatarService: AvatarService
): AvatarRepository {
    override suspend fun loginRecreation(avatarLoginRequest: AvatarLoginRequest): NetworkResult<AvatarResponse> {
        return handleApi(TAG) {
            avatarService.loginRecreation(avatarLoginRequest)
        }
    }

    override suspend fun createAvatar(avatarCreateRequest: AvatarCreateRequest): NetworkResult<AvatarResponse> {
        return handleApi(TAG) {
            avatarService.createAvatar(avatarCreateRequest)
        }
    }

    override suspend fun getUserAvatarList(): NetworkResult<List<AvatarUserResponse>> {
        return handleApi(TAG) {
            avatarService.getUserAvatarList()
        }
    }

}