package com.project.giunne.common.presentation.friend.state

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.DataThrowable

data class FriendState(
    val friendsList: List<AvatarUserResponse> = listOf(),
    val loading: Boolean = false,
    val error: DataThrowable? = null
)