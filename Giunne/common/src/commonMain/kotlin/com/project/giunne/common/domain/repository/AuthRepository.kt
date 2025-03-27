package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.remote.request.PlayerRequest
import com.project.giunne.common.data.remote.request.StudentSignupRequest
import com.project.giunne.common.data.remote.request.TeacherSignupRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.remote.response.IDExistInfo
import com.project.giunne.common.data.remote.response.RefreshResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.NetworkResult
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Query

interface AuthRepository {
    suspend fun teacherSignup(teacherSignupRequest: TeacherSignupRequest): NetworkResult<AuthResponse>
    suspend fun studentSignup(studentSignupRequest: StudentSignupRequest): NetworkResult<AuthResponse>
    suspend fun login(loginRequest: LoginRequest): NetworkResult<AuthResponse>
    suspend fun logout(): NetworkResult<String>
    suspend fun refresh(playerRequest: PlayerRequest): NetworkResult<RefreshResponse>
    suspend fun changePassword(loginRequest: LoginRequest): NetworkResult<AuthResponse>
    suspend fun checkExistId(loginId: String): NetworkResult<IDExistInfo>
}