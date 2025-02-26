package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.remote.request.PlayerRequest
import com.project.giunne.common.data.remote.request.StudentSignupRequest
import com.project.giunne.common.data.remote.request.TeacherSignupRequest
import com.project.giunne.common.data.remote.response.AuthResponse
import com.project.giunne.common.data.remote.response.RefreshResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_LOGIN
import com.project.giunne.common.data.util.DefineUrl.URL_LOGOUT
import com.project.giunne.common.data.util.DefineUrl.URL_REFRESH
import com.project.giunne.common.data.util.DefineUrl.URL_STUDENT_SIGNUP
import com.project.giunne.common.data.util.DefineUrl.URL_TEACHER_SIGNUP
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Url

interface AuthService {
    /* Signup API */
    @POST(URL_TEACHER_SIGNUP)
    suspend fun teacherSignup(
        @Body teacherSignupRequest: TeacherSignupRequest
    ): BaseResponse<AuthResponse>

    @POST(URL_STUDENT_SIGNUP)
    suspend fun studentSignup(
        @Body studentSignupRequest: StudentSignupRequest
    ): BaseResponse<AuthResponse>

    @POST(URL_LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): BaseResponse<AuthResponse>

    @POST(URL_LOGOUT)
    suspend fun logout(
    ): BaseResponse<String>

    @POST(URL_REFRESH)
    suspend fun refresh(
        @Body playerRequest: PlayerRequest
    ): BaseResponse<RefreshResponse>
}