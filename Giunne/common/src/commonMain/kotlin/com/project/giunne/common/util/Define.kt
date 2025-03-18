package com.project.giunne.common.util

import com.project.giunne.common.data.remote.response.AuthResponse

private const val TAG = "Define"
object Define {
    var playerId: Long
//        get() {
//            val id = PreferencesUtil.settingsRepository!!.playerPref.get().toLong()
//            GLog.d(TAG, "Get Player Id => $id")
//            return id
//        }
        get() = PreferencesUtil.settingsRepository!!.playerPref.get().toLong()
        set(value) { PreferencesUtil.settingsRepository!!.playerPref.set(value.toString()) }

    var userRole: String
        get() = PreferencesUtil.settingsRepository!!.rolePref.get()
        set(value) { PreferencesUtil.settingsRepository!!.rolePref.set(value) }

    var accessToken: String
//        get() {
//            val token = PreferencesUtil.settingsRepository!!.accessTokenPref.get()
//            GLog.d(TAG, "Get Access Token Event => $token")
//            return token
//        }
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
    }

    fun clearInfo() {
        playerId = 0
        userRole = ""
        accessToken = ""
        refreshToken = ""
    }
}