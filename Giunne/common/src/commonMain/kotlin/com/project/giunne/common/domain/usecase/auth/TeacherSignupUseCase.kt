package com.project.giunne.common.domain.usecase.auth

import com.project.giunne.common.data.remote.request.TeacherSignupRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AuthRepository

class TeacherSignupUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(teacherSignupRequest: TeacherSignupRequest): AuthResponse {
        return authRepository
            .teacherSignup(teacherSignupRequest = teacherSignupRequest)
            .successOr(AuthResponse())
    }
}