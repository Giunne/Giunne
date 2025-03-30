package com.project.giunne.common.presentation.mypage.state

import com.project.giunne.common.data.remote.response.AvatarInformationResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.DataThrowable

data class MyPageState(
    val isLoading: Boolean = false,
    val logoutDialog: Boolean = false,
    val modifyDialog: Boolean = false,
    val avatarInformation: AvatarInformationResponse = AvatarInformationResponse(),
    val userInfo: AvatarUserResponse = AvatarUserResponse(),
    val error: DataThrowable? = null
)