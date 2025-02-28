package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_AVATAR_CREATE
import com.project.giunne.common.data.util.DefineUrl.URL_AVATAR_LOGIN
import com.project.giunne.common.data.util.DefineUrl.URL_USER_AVATAR_LIST
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST

interface AvatarService {
    @POST(URL_AVATAR_LOGIN)
    suspend fun loginRecreation(
        @Body avatarLoginRequest: AvatarLoginRequest
    ): BaseResponse<AvatarResponse>

    @POST(URL_AVATAR_CREATE)
    suspend fun createAvatar(
        @Body avatarCreateRequest: AvatarCreateRequest
    ): BaseResponse<AvatarResponse>

    @GET(URL_USER_AVATAR_LIST)
    suspend fun getUserAvatarList(): BaseResponse<List<AvatarUserResponse>>
}