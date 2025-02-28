package com.project.giunne.common.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.local.preference.SettingRepository
import com.project.giunne.common.data.remote.request.LoginRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.auth.LoginUseCase
import com.project.giunne.common.domain.usecase.common.GetSchoolListUseCase
import com.project.giunne.common.presentation.login.intent.LoginEvent
import com.project.giunne.common.presentation.login.state.LoginState
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_STUDENT
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.Define.savePrefAuthInfo
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
    private val loginUseCase: LoginUseCase = KoinJavaComponent.get(
        LoginUseCase::class.java),
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
                setState { copy(loading = false) }
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
            }.onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
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

    fun dismissDialog() {
        scope.launch {
            _loginFailEffect.send(NON_FAIL)
        }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    init {
        Napier.d(tag = TAG) { "onCreate" }
        getLoginInfo()
    }

    companion object {
        const val NON_FAIL = ""
        const val LOGIN_FAIL = "login_fail"
    }
}