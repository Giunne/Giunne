package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserListResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.Body

interface AvatarRepository {
    suspend fun loginRecreation(
        avatarLoginRequest: AvatarLoginRequest
    ): NetworkResult<AvatarResponse>

    suspend fun createAvatar(
        avatarCreateRequest: AvatarCreateRequest
    ): NetworkResult<AvatarResponse>

    suspend fun getUserAvatarList(pageIndex: Int): NetworkResult<AvatarUserListResponse>
}