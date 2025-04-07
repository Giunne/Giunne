package com.project.giunne.common.presentation.home.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.CreateAvatarUseCase
import com.project.giunne.common.domain.usecase.avatar.LoginRecreationUseCase
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.roadmap.CreateRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetRecreationTeacherListUseCase
import com.project.giunne.common.presentation.home.teacher.state.TeacherHomeEvent
import com.project.giunne.common.presentation.home.teacher.state.TeacherHomeState
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherHomeComponent"
class TeacherHomeComponent(
    componentContext: ComponentContext,
    private val createRecreationUseCase: CreateRecreationUseCase = KoinJavaComponent.get(
        CreateRecreationUseCase::class.java),
    private val createAvatarUseCase: CreateAvatarUseCase = KoinJavaComponent.get(CreateAvatarUseCase::class.java),
    private val getRecreationTeacherListUseCase: GetRecreationTeacherListUseCase = KoinJavaComponent.get(GetRecreationTeacherListUseCase::class.java),
    private val loginRecreationUseCase: LoginRecreationUseCase = KoinJavaComponent.get(
        LoginRecreationUseCase::class.java),
    private val getUploadList: GetUploadList = KoinJavaComponent.get(GetUploadList::class.java),
): KoinComponent, ComponentContext by componentContext, BaseComponent<TeacherHomeState, TeacherHomeEvent>(
    initialState = TeacherHomeState()
) {
    init {
        GLog.d(TAG, "onCreate")
        scope.launch {
            if (Define.playerId != 0L) {
                async {
                    loginRecreation(Define.playerId)
                    AvatarUtil.getRecreationList(Define.playerId, 1)
                    getCurrentTeacherRecreation(Define.recreationId, 1)
                }.await()
            }
        }
    }

    fun loginRecreation(
        playerId: Long
    ) {
        setState { copy(isLoading = true) }
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
                        isLoading = false
                    )
                }
                // accessToken 업데이트
                Define.accessToken = response.accessToken
            }.onFailure {
                setState { copy(isLoading = false) }
                postSideEffect(TeacherHomeEvent.ShowSnackBar("진행중인 로드맵 정보를 불러오지 못했어요. 😭"))
            }
        }
    }

    fun createRecreation(
        recreationRequest: RecreationRequest
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                createRecreationUseCase(
                    recreationRequest = recreationRequest
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        showDialog = false,
                        createRecreationResult = response
                    )
                }
                postSideEffect(TeacherHomeEvent.CreateAvatar(recreationId = response.id))
            }.onFailure {
                postSideEffect(TeacherHomeEvent.ShowSnackBar("로드맵을 추가 하다가 실패했어요. 😭"))
            }
        }
    }

    fun autoCreateAvatar(
        recreationId: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                createAvatarUseCase(
                    AvatarCreateRequest(
                        recreationId = recreationId,
                        grade = 1,
                        classNumber = 1,
                        characterNo = 1,
                        studentNumber = 1,
                        nickName = ""
                    )
                )
            }.onSuccess {
                setState { copy(isLoading = false,) }
                postSideEffect(TeacherHomeEvent.ShowSnackBar("로드맵이 추가되었습니다! 👏🏼"))
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

    fun getCurrentTeacherRecreation(
        recreationId: Int,
        pageIndex: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getRecreationTeacherListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreation = response.data.find { it.id == recreationId } ?: Recreation()
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

    fun callTrainingUploadList() {
        scope.launch {
//            setState { copy(loading = true) }
            runCatching {
                getUploadList.invoke(1)
            }.onSuccess { response ->
                setState {
                    copy(
//                        loading = false,
                        certTrainingWaitList = response
                    )
                }
            }.onFailure {
                setState {
                    copy(
//                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun callRunningUploadList() {
        scope.launch {
//            setState { copy(loading = true) }
            runCatching {
                getUploadList.invoke(2)
            }.onSuccess { response ->
                setState {
                    copy(
//                        loading = false,
                        certRunningWaitList = response
                    )
                }
            }.onFailure {
                setState {
                    copy(
//                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun showDialog() {
        setState { copy(showDialog = true) }
    }

    fun dismissDialog() {
        setState { copy(showDialog = false) }
    }
}