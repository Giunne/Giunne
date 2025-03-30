package com.project.giunne.common.presentation.mypage.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.mypage.GetAvatarInformationUseCase
import com.project.giunne.common.presentation.mypage.intent.MyPageEvent
import com.project.giunne.common.presentation.mypage.state.MyPageState
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherMyPageComponent"
class TeacherMyPageComponent(
    componentContext: ComponentContext,
    private val getAvatarInformationUseCase: GetAvatarInformationUseCase = KoinJavaComponent.get(GetAvatarInformationUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<MyPageState, MyPageEvent>(initialState = MyPageState()){
    init {
        GLog.d(TAG, "onCreate")
        if (Define.playerId != 0L) {
            scope.launch {
                getMyInformation()
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