package com.project.giunne.common.presentation.mypage.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.AvatarModifyRequest
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.mypage.GetAvatarInformationUseCase
import com.project.giunne.common.domain.usecase.mypage.ModifyAvatarInformationUseCase
import com.project.giunne.common.presentation.mypage.intent.MyPageEvent
import com.project.giunne.common.presentation.mypage.state.MyPageState
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentMyPageComponent"
class StudentMyPageComponent(
    componentContext: ComponentContext,
    private val getAvatarInformationUseCase: GetAvatarInformationUseCase = KoinJavaComponent.get(GetAvatarInformationUseCase::class.java),
    private val modifyAvatarInformationUseCase: ModifyAvatarInformationUseCase = KoinJavaComponent.get(ModifyAvatarInformationUseCase::class.java),
): KoinComponent, ComponentContext by componentContext, BaseComponent<MyPageState, MyPageEvent>(initialState = MyPageState()){
    init {
        GLog.d(TAG, "onCreate")
        if (Define.playerId != 0L) {
            scope.launch {
                async {
                    AvatarUtil.getRecreationList(Define.playerId, 1)
                    getMyInformation()
                }.await()
            }
        }
    }

    fun getMyInformation() {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                getAvatarInformationUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        avatarInformation = response
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

    fun modifyAvatarInformation(
        nickName: String,
        grade: Int,
        classNumber: Int
    ) {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                modifyAvatarInformationUseCase(
                    AvatarModifyRequest(
                        playerId = Define.playerId.toInt(),
                        nickName = nickName,
                        grade = grade,
                        classNumber = classNumber,
                        studentNumber = 1
                    )
                )
            }.onSuccess {
                setState { copy(isLoading = false) }
                postSideEffect(MyPageEvent.SuccessModifyInformation("내 정보를 수정했습니다!"))
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

    fun showMyPageModifyDialog() {
        setState { copy(modifyDialog = true) }
    }

    fun dismissMyPageModifyDialog() {
        setState { copy(modifyDialog = false) }
    }

    fun onClickLogoutButton() {
        setState {
            copy(logoutDialog = true)
        }
    }

    fun dismissLogoutDialog() {
        setState {
            copy(logoutDialog = false)
        }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }
}