package com.project.giunne.common.presentation.mypage.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.mypage.intent.MyPageEvent
import com.project.giunne.common.presentation.mypage.state.MyPageState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentMyPageComponent"
class StudentMyPageComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,
BaseComponent<MyPageState, MyPageEvent>(initialState = MyPageState()){
    init {
        GLog.d(TAG, "onCreate")
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