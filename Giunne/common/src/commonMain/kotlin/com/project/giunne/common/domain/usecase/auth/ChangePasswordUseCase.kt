package com.project.giunne.common.domain.usecase.auth

import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AuthRepository

class ChangePasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): AuthResponse {
        return authRepository
            .changePassword(loginRequest = loginRequest)
            .successOr(AuthResponse())
    }
}