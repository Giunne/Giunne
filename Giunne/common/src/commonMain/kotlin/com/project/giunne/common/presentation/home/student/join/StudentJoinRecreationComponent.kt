package com.project.giunne.common.presentation.home.student.join

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.CreateAvatarUseCase
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.presentation.home.student.state.StudentJoinEvent
import com.project.giunne.common.presentation.home.student.state.StudentJoinState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentJoinRecreationComponent"
class StudentJoinRecreationComponent(
    componentContext: ComponentContext,
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(GetUserAvatarListUseCase::class.java),
    private val loginRecreationUseCase: LoginRecreationUseCase = KoinJavaComponent.get(LoginRecreationUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<StudentJoinState, StudentJoinEvent>(
    initialState = StudentJoinState()
) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun getJoinRecreationList() {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getAvatarListUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationStudentJoinList = response.mapToRecreation()
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun loginRecreation(
        avatarLoginRequest: AvatarLoginRequest
    ) {
        setState { copy(isLoading = false) }
        scope.launch {
            runCatching {
                loginRecreationUseCase(avatarLoginRequest)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        avatarInfo = response
                    )
                }
                postSideEffect(StudentJoinEvent.SuccessLogin("선택한 로드맵에 연결 되었습니다! 👏🏼"))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
                postSideEffect(StudentJoinEvent.SuccessLogin("로드맵 로그인에 실패했습니다."))
            }
        }
    }
}