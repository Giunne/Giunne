package com.project.giunne.common.util

import com.project.giunne.common.data.remote.response.AuthResponse

object Define {
    var authInfo: AuthResponse = AuthResponse()
    var playerId: Long = 0

    fun clearInfo() {
        authInfo = AuthResponse()
    }
}