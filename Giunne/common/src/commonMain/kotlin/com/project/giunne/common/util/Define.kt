package com.project.giunne.common.util

import com.project.giunne.common.data.remote.response.AuthResponse

object Define {
    var authInfo: AuthResponse = AuthResponse()
    var playerId: Long = 0

    var userRole: String
        get() = PreferencesUtil.settingsRepository!!.rolePref.get()
        set(value) { PreferencesUtil.settingsRepository!!.rolePref.set(value) }

    var accessToken: String
        get() = PreferencesUtil.settingsRepository!!.accessTokenPref.get()
        set(value) { PreferencesUtil.settingsRepository!!.accessTokenPref.set(value) }

    var refreshToken: String
        get() = PreferencesUtil.settingsRepository!!.refreshTokenPref.get()
        set(value) { PreferencesUtil.settingsRepository!!.refreshTokenPref.set(value) }

    fun savePrefAuthInfo(
        authResponse: AuthResponse
    ) {
        userRole = authResponse.role
        accessToken = authResponse.accessToken
        refreshToken = authResponse.refreshToken
    }

    fun changeAccessToken(
        accessToken: String
    ) {
        authInfo = authInfo.copy(accessToken = accessToken)
    }

    fun clearInfo() {
        authInfo = AuthResponse()
        userRole = ""
        accessToken = ""
        refreshToken = ""
        playerId = 0
    }
}