package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.remote.request.PasswordChangeRequest
import com.project.giunne.common.data.remote.request.PasswordResetRequest
import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserListResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.remote.response.MyPointInfo
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Query

interface AvatarRepository {
    suspend fun loginRecreation(
        avatarLoginRequest: AvatarLoginRequest
    ): NetworkResult<AvatarResponse>

    suspend fun createAvatar(
        avatarCreateRequest: AvatarCreateRequest
    ): NetworkResult<AvatarResponse>

    suspend fun getUserAvatarList(pageIndex: Int): NetworkResult<AvatarUserListResponse>
    suspend fun getRecreationAvatarList(recreationId: Long): NetworkResult<List<AvatarUserResponse>>
    suspend fun getPointInfo(): NetworkResult<MyPointInfo>
    suspend fun modifyStudentPoint(studentPointRequest: StudentPointRequest): NetworkResult<String>
    suspend fun modifyStudentExp(studentExpRequest: StudentExpRequest): NetworkResult<String>
    suspend fun resetPassword(passwordResetRequest: PasswordResetRequest): NetworkResult<String>
    suspend fun changeStudentPassword(passwordChangeRequest: PasswordChangeRequest): NetworkResult<String>
}