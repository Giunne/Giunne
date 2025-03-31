package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserListResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
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
        return handleApi(TAG) {
            handleTokenForResponse {
                avatarService.createAvatar(avatarCreateRequest)
            }
        }
    }

    override suspend fun getUserAvatarList(pageIndex: Int): NetworkResult<AvatarUserListResponse> {
        return handleApi(TAG) {
            handleTokenForResponse {
                avatarService.getUserAvatarList(pageIndex)
            }
        }
    }

    override suspend fun getRecreationAvatarList(recreationId: Long): NetworkResult<List<AvatarUserResponse>> {
//        val response = handleTokenForResponse { avatarService.getRecreationAvatarList(recreationId = recreationId) }

        return handleApi(TAG) {
            avatarService.getRecreationAvatarList(recreationId = recreationId)
        }
    }

    override suspend fun getPointInfo(): NetworkResult<MyPointInfo> {
        return handleApi(TAG) {
            handleTokenForResponse {
                avatarService.getPointInfo()
            }
        }
    }

    override suspend fun modifyStudentPoint(studentPointRequest: StudentPointRequest): NetworkResult<String> {
        return handleApi(TAG) {
            handleTokenForResponse {
                avatarService.modifyStudentPoint(studentPointRequest)
            }
        }
    }

    override suspend fun modifyStudentExp(studentExpRequest: StudentExpRequest): NetworkResult<String> {
        return handleApi(TAG) {
            handleTokenForResponse {
                avatarService.modifyStudentExp(studentExpRequest)
            }
        }
    }
}