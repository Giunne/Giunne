package com.project.giunne.common.presentation.home.teacher.recreation

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetRecreationTeacherListUseCase
import com.project.giunne.common.presentation.home.teacher.state.TeacherRecreationEvent
import com.project.giunne.common.presentation.home.teacher.state.TeacherRecreationState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherRecreationComponent"
class TeacherRecreationComponent(
    componentContext: ComponentContext,
    private val getRecreationTeacherListUseCase: GetRecreationTeacherListUseCase = KoinJavaComponent.get(
        GetRecreationTeacherListUseCase::class.java)
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
                getRecreationTeacherListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationList = response.data
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
                getRecreationTeacherListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        recreationList = (recreationList + response.data).distinctBy { it.id }
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
}