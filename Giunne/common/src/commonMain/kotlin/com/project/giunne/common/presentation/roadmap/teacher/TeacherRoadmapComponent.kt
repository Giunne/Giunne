package com.project.giunne.common.presentation.roadmap.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.request.QuestStateRequest
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.QuestStateInfo
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetFriendsListUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetAllRoadMapUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetTeacherCourseUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestInfoUseCase
import com.project.giunne.common.domain.usecase.roadmap.ModifyQuestStateUseCase
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapEvent
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherRoadmapComponent"
class TeacherRoadmapComponent(
    componentContext: ComponentContext,
    private val getAllRoadMapUseCase: GetAllRoadMapUseCase = KoinJavaComponent.get(GetAllRoadMapUseCase::class.java),
    private val getTeacherCourseUseCase: GetTeacherCourseUseCase = KoinJavaComponent.get(GetTeacherCourseUseCase::class.java),
    private val modifyQuestInfoUseCase: ModifyQuestInfoUseCase = KoinJavaComponent.get(ModifyQuestInfoUseCase::class.java),
    private val getFriendsListUseCase: GetFriendsListUseCase = KoinJavaComponent.get(GetFriendsListUseCase::class.java),
    private val modifyQuestStateUseCase: ModifyQuestStateUseCase = KoinJavaComponent.get(ModifyQuestStateUseCase::class.java),
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

    fun getTeacherCourse(
        id: Long
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getTeacherCourseUseCase(id)
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
        courseId: Long,
        id: Int,
        questDescription: String = "퀘스트 설명",
        trainingDescription: String = "퀘스트 방법",
        rewardPoint: Long,
        rewardExp: Long,
        guideUrl: String = "퀘스트 URL",
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                modifyQuestInfoUseCase(
                    modifyQuestInfoRequest = ModifyQuestInfoRequest(
                        id = id,
                        questDescription = questDescription,
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
                getTeacherCourse(courseId)
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


    fun loadStudentList(
        recreationId: Long,
        id: Int
    ) {
        println(id)
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getFriendsListUseCase(recreationId)
            }.onSuccess { response ->
                setState {
                    val course = courseMap.values.flatten()
                    val questInfo = course.find { it.id == id }?.questInfo ?: QuestInfo()
                    val studentList = questInfo.questStateInfos.map { questInfo ->
                        val name = response.find { it.id == questInfo.playerId }?.nickname.orEmpty()
                        questInfo.copy(name = name)
                    }.filter { it.name != "" }
                    copy(isLoading = false, studentList = studentList)
                }
            }
            .onFailure {
                setState { copy(isLoading = false, error = it.asDataThrowable()) }
            }
        }
    }

    fun modifyQuestState(
        checkIdSet: Set<Int>
    ) {
        scope.launch {
            runCatching {
                async {
                    checkIdSet.forEach { id ->
                        modifyQuestStateUseCase(
                            QuestStateRequest(
                                questStateId = id,
                                questProgress = "CHECK"
                            )
                        )
                    }
                }.await()
            }.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        isSuccess = true,
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

    fun dismissModifySuccessDialog() {
        setState { copy(modifySuccess = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    fun dismissSuccessDialog() {
        setState { copy(isSuccess = false) }
    }


    fun checkedStudent(studentCheck: QuestStateInfo, checked: Boolean) {
        setState {
            copy(
                studentList = studentList.toMutableList().apply {
                    val index = indexOf(studentCheck)
                    this[index] = studentCheck.copy(isChecked = checked)
                },
                checkedIdSet = if (checked) {
                    checkedIdSet + studentCheck.id
                } else {
                    checkedIdSet - studentCheck.id
                }
            )
        }
    }

    fun checkedStudentAll(allSelected: Boolean) {
        setState {
            copy(
                studentList = studentList.map { it.copy(isChecked = allSelected) },
                checkedIdSet = if (allSelected) {
                    studentList.map { it.id }.toSet()
                } else {
                    setOf()
                }
            )
        }
    }


    fun clearStudentCheckList() {
        setState {
            copy(checkedIdSet = setOf())
        }
    }
}