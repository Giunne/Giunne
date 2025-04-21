package com.project.giunne.common.presentation.home.student.join

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.presentation.home.student.state.StudentJoinEvent
import com.project.giunne.common.presentation.home.student.state.StudentJoinState
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define
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

    fun getJoinRecreationList(
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
                        recreationStudentJoinList = response.data.mapToRecreation()
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

    fun loadMore(pageIndex: Int) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationStudentJoinList = (recreationStudentJoinList + response.data.mapToRecreation()).distinctBy { it.id }
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
                        isLoading = false,
                        avatarInfo = response
                    )
                }
                // playerId, accessToken 업데이트
                Define.playerId = recreationId
                Define.recreationId = response.recreationId.toInt()
                Define.accessToken = response.accessToken
                AvatarUtil.getRecreationList(recreationId, 1)
                postSideEffect(StudentJoinEvent.SuccessLogin("선택한 로드맵에 연결 되었습니다! 👏🏼", playerId = recreationId))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
                postSideEffect(StudentJoinEvent.FailLogin("로드맵 로그인에 실패했습니다."))
            }
        }
    }
}