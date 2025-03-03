package com.project.giunne.common.presentation.mypage.state

import com.project.giunne.common.data.remote.response.AvatarUserResponse

data class MyPageState(
    val isLoading: Boolean = false,
    val logoutDialog: Boolean = false,
    val userInfo: AvatarUserResponse = AvatarUserResponse(),
)