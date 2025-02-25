package com.project.giunne.common.domain.usecase.auth

import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String {
        return authRepository
            .logout()
            .successOr("")
    }
}