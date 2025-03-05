package com.project.giunne.common.presentation.roadmap.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestInfoUseCase
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapEvent
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherRoadmapComponent"
class TeacherRoadmapComponent(
    componentContext: ComponentContext,
    private val getAllRoadMapUseCase: GetAllRoadMapUseCase = KoinJavaComponent.get(GetAllRoadMapUseCase::class.java),
    private val getCourseUseCase: GetCourseUseCase = KoinJavaComponent.get(GetCourseUseCase::class.java),
    private val modifyQuestInfoUseCase: ModifyQuestInfoUseCase = KoinJavaComponent.get(ModifyQuestInfoUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<RoadMapState, RoadMapEvent>(
    initialState = RoadMapState()
) {
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

    fun getCourse(
        id: Long
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getCourseUseCase(id)
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

    fun modifyQuestInfo(
        id: Int,
        description: String,
        trainingDescription: String,
        rewardPoint: Long,
        rewardExp: Long,
        guideUrl: String,
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                modifyQuestInfoUseCase(
                    modifyQuestInfoRequest = ModifyQuestInfoRequest(
                        id = id,
                        description = description,
                        trainingDescription = trainingDescription,
                        rewardPoint = rewardPoint,
                        rewardExp = rewardExp,
                        guideUrl = guideUrl
                    )
                )
            }.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        modifySuccess = true
                    )
                }
                getCourse(1)
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

    fun dismissModifySuccessDialog() {
        setState { copy(modifySuccess = false) }
    }
}