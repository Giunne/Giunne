package com.project.giunne.common.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.local.preference.SettingRepository
import com.project.giunne.common.data.remote.request.FCMRequest
import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.remote.request.PasswordChangeRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.auth.LoginUseCase
import com.project.giunne.common.domain.usecase.avatar.ChangeStudentPasswordUseCase
import com.project.giunne.common.domain.usecase.avatar.GetFCMTokenListUseCase
import com.project.giunne.common.domain.usecase.avatar.PostFCMTokenUseCase
import com.project.giunne.common.domain.usecase.common.GetSchoolListUseCase
import com.project.giunne.common.presentation.login.intent.LoginEvent
import com.project.giunne.common.presentation.login.state.LoginState
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_STUDENT
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.Define.savePrefAuthInfo
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.studentID
import com.project.giunne.common.util.studentPass
import com.project.giunne.common.util.teacherID
import com.project.giunne.common.util.teacherPass
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "LoginComponent"
class LoginComponent(
    componentContext: ComponentContext,
    private val prefRepository: SettingRepository,
    val goToStudentMain: () -> Unit,
    val goToTeacherMain: () -> Unit,
    val getFirebaseToken: suspend () -> String?,
    private val loginUseCase: LoginUseCase = KoinJavaComponent.get(
        LoginUseCase::class.java),
    private val changeStudentPasswordUseCase: ChangeStudentPasswordUseCase = KoinJavaComponent.get(ChangeStudentPasswordUseCase::class.java),
    private val getFCMTokenListUseCase: GetFCMTokenListUseCase = KoinJavaComponent.get(GetFCMTokenListUseCase::class.java),
    private val postFCMTokenUseCase: PostFCMTokenUseCase = KoinJavaComponent.get(PostFCMTokenUseCase::class.java),
): KoinComponent, ComponentContext by componentContext,
BaseComponent<LoginState, LoginEvent>(
    initialState = LoginState()
){
    init {
        getLoginInfo()
    }

    private val _loginFailEffect = Channel<String>()
    val loginFailEffect = _loginFailEffect.receiveAsFlow()

    internal fun onLoginButtonClick(
        loginRequest: LoginRequest
    ) {
        scope.launch {
            setState { copy(loading = true) }

            runCatching {
                loginUseCase.invoke(loginRequest)
            }.onSuccess { response ->
//                Define.authInfo = response
                savePrefAuthInfo(response)
                saveLoginInfo(loginRequest.loginId)
                callFCMTokenList(response.memberId)
                setState { copy(loading = false) }
                if (loginRequest.password.length < 8) { // 초기화 신호
                    setState { copy(passwordSetupDialog = true) }
                } else {
                    when(response.role) {
                        TYPE_TEACHER -> {
                            withContext(Dispatchers.Main) {
                                goToTeacherMain()
                            }
                        }
                        TYPE_STUDENT -> {
                            withContext(Dispatchers.Main) {
                                goToStudentMain()
                            }
                        }
                    }
                }
            }.onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    fun callFCMTokenList(
        memberId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getFCMTokenListUseCase.invoke(memberId)
            }.onSuccess { response ->
                val FCMToken = getFirebaseToken()
                if (FCMToken != null) {
                    val existToken = response.find { it.token == FCMToken }
                    if (existToken == null) { // 새로운 토큰 발생
                        callPostFCMToken(
                            fcmRequest = FCMRequest(
                                token = FCMToken
                            )
                        )
                    } else {
                        GLog.d("FCM 토큰", "기존 FCM토큰 있음 : $FCMToken")
                    }
                }
                setState { copy(loading = false) }
            }.onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    suspend fun callPostFCMToken(
        fcmRequest: FCMRequest
    ) {
        runCatching {
            postFCMTokenUseCase.invoke(fcmRequest)
        }.onSuccess { response ->
            GLog.d("FCM 토큰", "기존 FCM토큰 없음, 서버로 전송 : ${fcmRequest.token}")
        }.onFailure {
            setState { copy(error = it.asDataThrowable()) }
        }
    }

    fun callChangePasswordStudent(
        password: String
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                changeStudentPasswordUseCase.invoke(
                    passwordChangeRequest = PasswordChangeRequest(password)
                )
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        passwordChangeSuccessDialog = true,
                        passwordSetupDialog = false
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun onIdTextChanged(
        text: String
    ) {
        setState { copy(idText = text) }
    }

    fun onPassTextChanged(
        text: String
    ) {
        setState { copy(passText = text) }
    }

    private fun getLoginInfo() {
        setState {
            copy(idText = prefRepository.idPref.get())
        }
    }

    private fun saveLoginInfo(
        idText: String
    ) {
        prefRepository.idPref.set(idText)
    }

    fun dismissPasswordSetupDialog() {
        setState { copy(passwordSetupDialog = false) }
    }

    fun dismissPasswordChangeSuccessDialog() {
        setState { copy(passwordChangeSuccessDialog = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    init {
        Napier.d(tag = TAG) { "onCreate" }
        getLoginInfo()
    }

    companion object {
    }
}