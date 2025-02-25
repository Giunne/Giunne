package com.project.giunne.common.domain.usecase.auth

import com.project.giunne.common.data.remote.request.StudentSignupRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AuthRepository

class StudentSignupUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(studentSignupRequest: StudentSignupRequest): AuthResponse {
        return authRepository
            .studentSignup(studentSignupRequest = studentSignupRequest)
            .successOr(AuthResponse())
    }
}