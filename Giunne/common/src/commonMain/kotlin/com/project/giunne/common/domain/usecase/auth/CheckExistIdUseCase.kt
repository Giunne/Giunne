package com.project.giunne.common.domain.usecase.auth

import com.project.giunne.common.data.remote.response.IDExistInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AuthRepository

class CheckExistIdUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginId: String): IDExistInfo {
        return authRepository
            .checkExistId(loginId = loginId)
            .successOr(IDExistInfo())
    }
}