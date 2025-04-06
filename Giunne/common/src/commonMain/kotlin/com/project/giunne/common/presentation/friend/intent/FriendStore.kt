package com.project.giunne.common.presentation.friend.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.request.PasswordResetRequest
import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.WearingItem
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetFriendsListUseCase
import com.project.giunne.common.domain.usecase.avatar.ModifyStudentExpUseCase
import com.project.giunne.common.domain.usecase.avatar.ModifyStudentPointUseCase
import com.project.giunne.common.domain.usecase.avatar.ResetPasswordUseCase
import com.project.giunne.common.domain.usecase.roadmap.GetSpecificStudentCourseUseCase
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.friend.state.FriendState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class FriendStore(
    private val getFriendsListUseCase: GetFriendsListUseCase = KoinJavaComponent.get(GetFriendsListUseCase::class.java),
    private val getSpecificStudentCourseUseCase: GetSpecificStudentCourseUseCase = KoinJavaComponent.get(GetSpecificStudentCourseUseCase::class.java),
    private val modifyStudentPointUseCase: ModifyStudentPointUseCase = KoinJavaComponent.get(ModifyStudentPointUseCase::class.java),
    private val modifyStudentExpUseCase: ModifyStudentExpUseCase = KoinJavaComponent.get(ModifyStudentExpUseCase::class.java),
    private val resetPasswordUseCase: ResetPasswordUseCase = KoinJavaComponent.get(ResetPasswordUseCase::class.java)
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
//                    copy(loading = false, friendsList = response.filter { it.id != id && it.nickname.isNotEmpty() })
                    copy(loading = false, friendsList = response.filter { it.nickname.isNotEmpty() })
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

    fun callResetPassword(
        passwordResetRequest: PasswordResetRequest
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                resetPasswordUseCase.invoke(passwordResetRequest)
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        resetSuccessDialog = true,
                        selectedResetConfirmAvatar = null
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
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

    fun modifyStudentPoint(
        studentPointRequest: StudentPointRequest,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                modifyStudentPointUseCase.invoke(studentPointRequest)
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        showPointModifySuccessDialog = true
                    )
                }
                onSuccess()
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }

    }

    fun callModifyStudentExp(
        studentExpRequest: StudentExpRequest,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                modifyStudentExpUseCase.invoke(studentExpRequest)
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        showExpModifySuccessDialog = true
                    )
                }
                onSuccess()
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun selectPointAvatar(
        avatar: AvatarUserResponse
    ) {
        setState { copy(selectedPointAvatar = avatar) }
    }

    fun selectExpAvatar(
        avatar: AvatarUserResponse
    ) {
        setState { copy(selectedExpAvatar = avatar) }
    }

    fun selectResetPasswordAvatar(
        avatar: AvatarUserResponse
    ) {
        setState { copy(selectedResetConfirmAvatar = avatar) }
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

    fun showFriendLargeAvatar(wearingItems: List<WearingItem>) {
        setState {
            copy(
                showFriendLargeAvatar = true,
                wearingItems = wearingItems
            )
        }
    }

    fun dismissFriendLargeAvatar() {
        setState { copy(showFriendLargeAvatar = false) }
    }

    fun showModifyCheckDialog() {
        setState { copy(showModifyCheckDialog = true) }
    }

    fun dismissModifyPointDialog() {
        setState { copy(selectedPointAvatar = null) }
    }

    fun onInvalidNumeric() {
        setState { copy(invalidNumericDialog = true) }
    }

    fun dismissInvalidNumericDialog() {
        setState { copy(invalidNumericDialog = false) }
    }

    fun dismissModifyExpDialog() {
        setState { copy(selectedExpAvatar = null) }
    }

    fun dismissModifyCheckDialog() {
        setState { copy(showModifyCheckDialog = false) }
    }

    fun dismissPointModifySuccessDialog() {
        setState { copy(showPointModifySuccessDialog = false) }
    }

    fun dismissExpModifySuccessDialog() {
        setState { copy(showExpModifySuccessDialog = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    fun dismissStudentRoadMapDialog() {
        setState { copy(showStudentCourse = false) }
    }

    fun dismissResetConfirmDialog() {
        setState { copy(selectedResetConfirmAvatar = null) }
    }

    fun dismissResetSuccessDialog() {
        setState { copy(resetSuccessDialog = false) }
    }
}