package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.request.FCMRequest
import com.project.giunne.common.data.remote.request.PasswordChangeRequest
import com.project.giunne.common.data.remote.request.PasswordResetRequest
import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserListResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.FCMResponse
import com.project.giunne.common.data.remote.response.MyPointInfo
import com.project.giunne.common.data.service.AvatarService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.AvatarRepository
import com.project.giunne.common.util.Define.recreationId

private const val TAG = "AvatarRepositoryImpl"
class AvatarRepositoryImpl(
    private val avatarService: AvatarService
): AvatarRepository {
    override suspend fun loginRecreation(avatarLoginRequest: AvatarLoginRequest): NetworkResult<AvatarResponse> {
        val response = handleTokenForResponse { avatarService.loginRecreation(avatarLoginRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun createAvatar(avatarCreateRequest: AvatarCreateRequest): NetworkResult<AvatarResponse> {
        val response = handleTokenForResponse { avatarService.createAvatar(avatarCreateRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getUserAvatarList(pageIndex: Int): NetworkResult<AvatarUserListResponse> {
        val response = handleTokenForResponse { avatarService.getUserAvatarList(pageIndex) }

        return handleApi(TAG) { response }
    }

    override suspend fun getRecreationAvatarList(recreationId: Long): NetworkResult<List<AvatarUserResponse>> {
        val response = handleTokenForResponse { avatarService.getRecreationAvatarList(recreationId = recreationId) }

        return handleApi(TAG) { response }
    }

    override suspend fun getPointInfo(): NetworkResult<MyPointInfo> {
        val response = handleTokenForResponse { avatarService.getPointInfo() }

        return handleApi(TAG) { response }
    }

    override suspend fun modifyStudentPoint(studentPointRequest: StudentPointRequest): NetworkResult<String> {
        val response = handleTokenForResponse { avatarService.modifyStudentPoint(studentPointRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun modifyStudentExp(studentExpRequest: StudentExpRequest): NetworkResult<String> {
        val response = handleTokenForResponse { avatarService.modifyStudentExp(studentExpRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun resetPassword(passwordResetRequest: PasswordResetRequest): NetworkResult<String> {
        val response = handleTokenForResponse { avatarService.resetPassword(passwordResetRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun changeStudentPassword(passwordChangeRequest: PasswordChangeRequest): NetworkResult<String> {
        val response = handleTokenForResponse { avatarService.changeStudentPassword(passwordChangeRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getFCMTokenList(memberId: Long): NetworkResult<List<FCMResponse>> {
        val response = handleTokenForResponse { avatarService.getFCMTokenList(memberId) }

        return handleApi(TAG) { response }
    }

    override suspend fun postFCMToken(fcmRequest: FCMRequest): NetworkResult<String> {
        val response = handleTokenForResponse { avatarService.postFCMToken(fcmRequest) }

        return handleApi(TAG) { response }
    }
}