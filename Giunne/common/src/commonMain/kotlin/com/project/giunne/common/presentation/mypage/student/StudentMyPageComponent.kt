package com.project.giunne.common.presentation.mypage.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.presentation.mypage.intent.MyPageEvent
import com.project.giunne.common.presentation.mypage.state.MyPageState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentMyPageComponent"
class StudentMyPageComponent(
    componentContext: ComponentContext,
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(GetUserAvatarListUseCase::class.java),
): KoinComponent, ComponentContext by componentContext,

BaseComponent<MyPageState, MyPageEvent>(initialState = MyPageState()){
    init {
        GLog.d(TAG, "onCreate")
    }

    fun getRecreationList(
        playerId: Long,
        pageIndex: Int
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        userInfo = response.data.find { it.id.toLong() == playerId } ?: AvatarUserResponse()
                    )
                }
            }.onFailure {
                setState { copy(isLoading = false) }
            }
        }
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
}