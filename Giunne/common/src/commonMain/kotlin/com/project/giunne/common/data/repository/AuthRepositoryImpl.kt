package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.remote.request.PlayerRequest
import com.project.giunne.common.data.remote.request.StudentSignupRequest
import com.project.giunne.common.data.remote.request.TeacherSignupRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.remote.response.RefreshResponse
import com.project.giunne.common.data.service.AuthService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.AuthRepository
import com.project.giunne.common.util.GLog

private const val TAG = "AuthRepositoryImpl"
class AuthRepositoryImpl(
    private val authService: AuthService
): AuthRepository {
    override suspend fun teacherSignup(teacherSignupRequest: TeacherSignupRequest): NetworkResult<AuthResponse> {
        return handleApi(TAG) {
            authService.teacherSignup(teacherSignupRequest = teacherSignupRequest)
        }
    }

    override suspend fun studentSignup(studentSignupRequest: StudentSignupRequest): NetworkResult<AuthResponse> {
        return handleApi(TAG) {
            authService.studentSignup(studentSignupRequest = studentSignupRequest)
        }
    }

    override suspend fun login(loginRequest: LoginRequest): NetworkResult<AuthResponse> {
        return handleApi(TAG) {
            authService.login(loginRequest = loginRequest)
        }
    }

    override suspend fun logout(): NetworkResult<String> {
        return handleApi(TAG) {
            authService.logout()
        }
    }

    override suspend fun refresh(playerRequest: PlayerRequest): NetworkResult<RefreshResponse> {
        return handleApi(TAG) {
            authService.refresh(playerRequest = playerRequest)
        }
    }
}