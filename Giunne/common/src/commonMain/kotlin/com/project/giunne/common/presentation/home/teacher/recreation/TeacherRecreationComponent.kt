package com.project.giunne.common.presentation.home.teacher.recreation

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.presentation.home.teacher.state.TeacherRecreationEvent
import com.project.giunne.common.presentation.home.teacher.state.TeacherRecreationState
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherRecreationComponent"
class TeacherRecreationComponent(
    componentContext: ComponentContext,
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(
        GetUserAvatarListUseCase::class.java),
    private val loginRecreationUseCase: LoginRecreationUseCase = KoinJavaComponent.get(
        LoginRecreationUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<TeacherRecreationState, TeacherRecreationEvent>(
    initialState = TeacherRecreationState()
) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun getTeacherRecreationList(
        pageIndex: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationList = response.data.mapToRecreation().filter { it.teacherLoginId == Define.loginId }
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

    fun loadMore(
        pageIndex: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationList = ((recreationList + response.data.mapToRecreation()).distinctBy { it.id }).filter { it.teacherLoginId == Define.loginId }
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
        recreationId: Long,
    ) {
        setState { copy(isLoading = false) }
        scope.launch {
            runCatching {
                loginRecreationUseCase(
                    AvatarLoginRequest(
                        playerId = recreationId
                    )
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false
                    )
                }
                Define.playerId = recreationId
                Define.recreationId = response.recreationId.toInt()
                Define.accessToken = response.accessToken
                postSideEffect(TeacherRecreationEvent.SuccessLogin(recreationId))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
                postSideEffect(TeacherRecreationEvent.ShowSnackBar("로드맵 로그인에 실패했습니다."))
            }
        }
    }
}