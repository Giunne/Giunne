package com.project.giunne.common.presentation.home.student.home

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.presentation.home.student.state.StudentHomeEvent
import com.project.giunne.common.presentation.home.student.state.StudentHomeState
import com.project.giunne.common.presentation.home.student.state.StudentJoinEvent
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentHomeComponent"
class StudentHomeComponent(
    componentContext: ComponentContext,
    private val loginRecreationUseCase: LoginRecreationUseCase = KoinJavaComponent.get(LoginRecreationUseCase::class.java),
): KoinComponent, ComponentContext by componentContext, BaseComponent<StudentHomeState, StudentHomeEvent>(
    initialState = StudentHomeState()
) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun loginRecreation(
        playerId: Long
    ) {
        setState { copy(isLoading = false) }
        scope.launch {
            runCatching {
                loginRecreationUseCase(
                    AvatarLoginRequest(
                        playerId = playerId
                    )
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        avatarInfo = response
                    )
                }
                // accessToken 업데이트
                Define.accessToken = response.accessToken
            }.onFailure {
                setState { copy(isLoading = false) }
                postSideEffect(StudentHomeEvent.ErrorSnackBar("진행중인 로드맵 정보를 불러오지 못했어요. 😭"))
            }
        }
    }
}