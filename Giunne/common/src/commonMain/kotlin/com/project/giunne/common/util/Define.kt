package com.project.giunne.common.util

import com.project.giunne.common.data.remote.response.AuthResponse

object Define {
    var playerId: Long
        get() = PreferencesUtil.settingsRepository!!.playerPref.get().toLong()
        set(value) { PreferencesUtil.settingsRepository!!.playerPref.set(value.toString()) }

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
        value: String
    ) {
        accessToken = value
//        authInfo = authInfo.copy(accessToken = accessToken)
    }

    fun clearInfo() {
//        authInfo = AuthResponse()
        userRole = ""
        accessToken = ""
        refreshToken = ""
        playerId = 0
    }
}