package com.project.giunne.common.presentation.select

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.CreateAvatarUseCase
import com.project.giunne.common.presentation.select.state.SelectCharacterEvent
import com.project.giunne.common.presentation.select.state.SelectCharacterUiState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

class StudentSelectCharacterComponent(
    componentContext: ComponentContext,
    private val createAvatarUseCase: CreateAvatarUseCase = KoinJavaComponent.get(CreateAvatarUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<SelectCharacterUiState, SelectCharacterEvent>(
    initialState = SelectCharacterUiState()
) {

    fun createAvatar(
        recreationId: Int,
        characterNo: Int,
        grade: Int,
        classNo: Int,
        name: String
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                createAvatarUseCase(
                    AvatarCreateRequest(
                        recreationId = recreationId,
                        grade = grade,
                        classNumber = classNo,
                        characterNo = characterNo,
                        nickName = name
                    )
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        successJoinDialog = true,
                        avatarResponse = response
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

    fun dismissSuccessJoinDialog() {
        setState { copy(successJoinDialog = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }
}