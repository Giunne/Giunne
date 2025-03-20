package com.project.giunne.common.presentation.mypage.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.presentation.mypage.intent.MyPageEvent
import com.project.giunne.common.presentation.mypage.state.MyPageState
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentMyPageComponent"
class StudentMyPageComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,

BaseComponent<MyPageState, MyPageEvent>(initialState = MyPageState()){
    init {
        GLog.d(TAG, "onCreate")
        if (Define.playerId != 0L) {
            AvatarUtil.getRecreationList(Define.playerId, 1)
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