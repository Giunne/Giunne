package com.project.giunne.common.presentation.roadmap.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetStudentCourseUseCase
import com.project.giunne.common.presentation.roadmap.student.state.StudentRoadMapEvent
import com.project.giunne.common.presentation.roadmap.student.state.StudentRoadMapState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentRoadmapComponent"
class StudentRoadmapComponent(
    componentContext: ComponentContext,
    private val getAllRoadMapUseCase: GetAllRoadMapUseCase = KoinJavaComponent.get(GetAllRoadMapUseCase::class.java),
    private val getStudentRoadMapUseCase: GetStudentCourseUseCase = KoinJavaComponent.get(GetStudentCourseUseCase::class.java),
): KoinComponent, ComponentContext by componentContext, BaseComponent<StudentRoadMapState, StudentRoadMapEvent>(
    initialState = StudentRoadMapState()
) {
    init {
        GLog.d(TAG, "onCreate")
    }

    fun getAllRoadMap() {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getAllRoadMapUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        roadMapInfo = response
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


    fun getStudentCourse(
        id: Long
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getStudentRoadMapUseCase(id)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        courseMap = response.courseInfo
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