package com.project.giunne.common.presentation.friend.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetFriendsListUseCase
import com.project.giunne.common.domain.usecase.avatar.ModifyStudentPointUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSpecificStudentCourseUseCase
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.friend.state.FriendState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class FriendStore(
    private val getFriendsListUseCase: GetFriendsListUseCase = KoinJavaComponent.get(GetFriendsListUseCase::class.java),
    private val getSpecificStudentCourseUseCase: GetSpecificStudentCourseUseCase = KoinJavaComponent.get(GetSpecificStudentCourseUseCase::class.java),
    private val modifyStudentPointUseCase: ModifyStudentPointUseCase = KoinJavaComponent.get(ModifyStudentPointUseCase::class.java)
): BaseStore<FriendState>(
    initialState = FriendState()
) {
    fun getFriendsList(
        recreationId: Long,
        id: Int
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getFriendsListUseCase(recreationId)
            }.onSuccess { response ->
                setState {
                    copy(loading = false, friendsList = response.filter { it.id != id && it.nickname.isNotEmpty() })
                }
            }
            .onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    fun getStudentList(
        recreationId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getFriendsListUseCase(recreationId)
            }.onSuccess { response ->
                setState {
                    copy(loading = false, friendsList = response)
                }
            }
            .onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    fun getSpecificStudentCourse(
        roadmapId: Long,
        playerId: Int
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getSpecificStudentCourseUseCase(roadmapId, playerId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        showStudentCourse = true,
                        courseMap = response.courseInfo
                    )
                }
            }
            .onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    fun setRoadMapId(
        pageType: CertPage,
        roadmapId: Long
    ) {
        setState {
            copy(
                pageType = pageType,
                roadmapId = roadmapId
            )
        }
    }

    fun modifyStudentPoint() {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                    val list = uiState.value.friendsList.filter { it.point != it.pointBuffer }
                    async {
                        list.forEach { student ->
                            modifyStudentPointUseCase(
                                StudentPointRequest(
                                    student.id,
                                    student.pointBuffer
                                )
                            )
                        }
                    }.await()
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        showModifySuccessDialog = true
                    )
                }
            }
            .onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }

    }

    fun modifyStudentPointLocal(index: Int, point: String) {
        setState {
            copy(
                friendsList = friendsList.toMutableList().apply {
                    this[index] = this[index].copy(pointBuffer = if (point.isEmpty()) 0 else point.toInt())
                }
            )
        }
    }

    fun showModifyCheckDialog() {
        setState { copy(showModifyCheckDialog = true) }
    }

    fun dismissModifyCheckDialog() {
        setState { copy(showModifyCheckDialog = false) }
    }

    fun dismissModifySuccessDialog() {
        setState { copy(showModifySuccessDialog = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    fun dismissStudentRoadMapDialog() {
        setState { copy(showStudentCourse = false) }
    }
}