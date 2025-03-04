package com.project.giunne.common.presentation.home.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.RecreationRequest
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.CreateRecreationUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetRecreationTeacherListUseCase
import com.project.giunne.common.presentation.home.teacher.state.TeacherHomeEvent
import com.project.giunne.common.presentation.home.teacher.state.TeacherHomeState
import com.project.giunne.common.util.DpUtil.res
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherHomeComponent"
class TeacherHomeComponent(
    componentContext: ComponentContext,
    private val createRecreationUseCase: CreateRecreationUseCase = KoinJavaComponent.get(
        CreateRecreationUseCase::class.java),
    private val getRecreationTeacherListUseCase: GetRecreationTeacherListUseCase = KoinJavaComponent.get(GetRecreationTeacherListUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<TeacherHomeState, TeacherHomeEvent>(
    initialState = TeacherHomeState()
) {
    init {
        GLog.d(TAG, "onCreate")
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
                postSideEffect(TeacherHomeEvent.ShowSnackBar("로드맵이 추가되었습니다! 👏🏼"))
            }.onFailure {
                postSideEffect(TeacherHomeEvent.ShowSnackBar("로드맵을 추가 하다가 실패했어요. 😭"))
            }
        }
    }

    fun getTeacherRecreation(
        teacherId: Long,
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
                        recreation = response.data.find { it.id.toLong() == teacherId } ?: Recreation()
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

    fun showDialog() {
        setState { copy(showDialog = true) }
    }

    fun dismissDialog() {
        setState { copy(showDialog = false) }
    }
}