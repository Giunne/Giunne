package com.project.giunne.common.presentation.roadmap.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSpecificStudentCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetStudentCourseUseCase
import com.project.giunne.common.presentation.roadmap.student.state.StudentRoadMapEvent
import com.project.giunne.common.presentation.roadmap.student.state.StudentRoadMapState
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentRoadmapComponent"
class StudentRoadmapComponent(
    componentContext: ComponentContext,
    private val getAllRoadMapUseCase: GetAllRoadMapUseCase = KoinJavaComponent.get(GetAllRoadMapUseCase::class.java),
    private val getStudentRoadMapUseCase: GetStudentCourseUseCase = KoinJavaComponent.get(GetStudentCourseUseCase::class.java),
    private val getSpecificStudentCourseUseCase: GetSpecificStudentCourseUseCase = KoinJavaComponent.get(GetSpecificStudentCourseUseCase::class.java),
): KoinComponent, ComponentContext by componentContext, BaseComponent<StudentRoadMapState, StudentRoadMapEvent>(
    initialState = StudentRoadMapState()
) {
    init {
        GLog.d(TAG, "onCreate")
        scope.launch {
            async {
                checkStudentRoadMapState(Define.currentExerciseId, 1, Define.playerId.toInt())
                checkStudentRoadMapState(Define.currentJoggingId, 2, Define.playerId.toInt())
            }.await()
        }
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


    private fun checkStudentRoadMapState(
        currentRoadMapId: Int,
        roadmapId: Long,
        playerId: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getSpecificStudentCourseUseCase(roadmapId, playerId)
            }.onSuccess { response ->
                val questInfo = getStudentCurrentRoadMapOrNull(currentRoadMapId, response.courseInfo.values.flatten())
                val questName = questInfo?.questName.orEmpty()
                val questState = questInfo?.questStateInfo?.questProgress.orEmpty()
                when (roadmapId) {
                    1L -> {
                        setState {
                            copy(
                                isLoading = false,
                                exerciseQuestName = questName,
                                showSuccessExerciseDialog = questState == "CONFIRM"
                            )
                        }
                    }
                    2L -> {
                        setState {
                            copy(
                                isLoading = false,
                                joggingQuestName = questName,
                                showSuccessJoggingDialog = questState == "CONFIRM"
                            )
                        }
                    }
                }
            }
                .onFailure {
                    setState {
                        copy(isLoading = false,
                            error = it.asDataThrowable())
                    }
                }
        }
    }

    private fun getStudentCurrentRoadMapOrNull(
        roadmapId: Int,
        list: List<StudentCourseInfo>
    ): StudentQuestInfo? {
        return list
            .find { it.questInfo.id == roadmapId }
            ?.questInfo
    }

    fun dismissSuccessExerciseDialog() {
        setState {
            copy(
                showSuccessExerciseDialog = false,
                exerciseQuestName = ""
            )
        }
    }

    fun dismissSuccessJoggingDialog() {
        setState {
            copy(
                showSuccessJoggingDialog = false,
                joggingQuestName = ""
            )
        }
    }
}