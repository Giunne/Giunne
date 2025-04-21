package com.project.giunne.common.util

import com.project.giunne.BuildKonfig
import com.project.giunne.common.data.remote.response.AuthResponse

private const val TAG = "Define"
object Define {
    val versionName: String
        get() = BuildKonfig.appVersionName

    val loginId: String
        get() = PreferencesUtil.settingsRepository!!.idPref.get()

    var playerId: Long
        get() = PreferencesUtil.settingsRepository!!.playerPref.get().toLong()
        set(value) { PreferencesUtil.settingsRepository!!.playerPref.set(value.toString()) }

    var memberId: Long
        get() = PreferencesUtil.settingsRepository!!.memberPref.get().toLong()
        set(value) { PreferencesUtil.settingsRepository!!.memberPref.set(value.toString()) }

    var recreationId: Int
        get() = PreferencesUtil.settingsRepository!!.recreationIdPref.get().toInt()
        set(value) { PreferencesUtil.settingsRepository!!.recreationIdPref.set(value.toString()) }

    var userRole: String
        get() = PreferencesUtil.settingsRepository!!.rolePref.get()
        set(value) { PreferencesUtil.settingsRepository!!.rolePref.set(value) }

    var accessToken: String
        get() = PreferencesUtil.settingsRepository!!.accessTokenPref.get()
        set(value) { PreferencesUtil.settingsRepository!!.accessTokenPref.set(value) }

    var refreshToken: String
        get() = PreferencesUtil.settingsRepository!!.refreshTokenPref.get()
        set(value) { PreferencesUtil.settingsRepository!!.refreshTokenPref.set(value) }

    var currentExerciseId: Int
        get() = PreferencesUtil.settingsRepository!!.currentExerciseIdPref.get().toInt()
        set(value) { PreferencesUtil.settingsRepository!!.currentExerciseIdPref.set(value.toString()) }

    var currentJoggingId: Int
        get() = PreferencesUtil.settingsRepository!!.currentJoggingIdPref.get().toInt()
        set(value) { PreferencesUtil.settingsRepository!!.currentJoggingIdPref.set(value.toString()) }

    var currentLevel: Int
        get() = PreferencesUtil.settingsRepository!!.currentLevelPref.get().toInt()
        set(value) { PreferencesUtil.settingsRepository!!.currentLevelPref.set(value.toString()) }

    var currentPlayerId: Int
        get() = PreferencesUtil.settingsRepository!!.currentPlayerIdPref.get().toInt()
        set(value) { PreferencesUtil.settingsRepository!!.currentPlayerIdPref.set(value.toString()) }

    fun savePrefAuthInfo(
        authResponse: AuthResponse
    ) {
        userRole = authResponse.role
        memberId = authResponse.memberId
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
        memberId = 0
        userRole = ""
        accessToken = ""
        refreshToken = ""
    }
}