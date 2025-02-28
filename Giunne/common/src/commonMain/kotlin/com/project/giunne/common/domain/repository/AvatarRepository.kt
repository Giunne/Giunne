package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.NetworkResult

interface AvatarRepository {
    suspend fun loginRecreation(
        avatarLoginRequest: AvatarLoginRequest
    ): NetworkResult<AvatarResponse>

    suspend fun createAvatar(
        avatarCreateRequest: AvatarCreateRequest
    ): NetworkResult<AvatarResponse>

    suspend fun getUserAvatarList(): NetworkResult<List<AvatarUserResponse>>
}