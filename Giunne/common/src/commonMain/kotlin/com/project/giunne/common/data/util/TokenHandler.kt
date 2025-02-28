package com.project.giunne.common.data.util

import com.project.giunne.common.data.remote.request.PlayerRequest
import com.project.giunne.common.data.remote.response.RefreshResponse
import com.project.giunne.common.data.service.AuthService
import com.project.giunne.common.domain.usecase.auth.LogoutUseCase
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

private const val TAG = "TokenHandler"
object TokenHandler {
    val authService: AuthService = KoinJavaComponent.get(AuthService::class.java)
    var isRefreshing: Boolean = false
    private val tokenFlow = MutableSharedFlow<String>(replay = 1)

    private val scope = CoroutineScope(Dispatchers.IO)
    private val _expireEffects = Channel<Boolean>()
    val expireEffects: Flow<Boolean> = _expireEffects.receiveAsFlow()

    private val logoutUseCase: LogoutUseCase = KoinJavaComponent.get(LogoutUseCase::class.java)

    suspend fun <T> handleTokenForResponse(block: suspend () -> BaseResponse<T>): BaseResponse<T> {
        val response = block()

        if (response.code == 401) {
            if (!isRefreshing) {
                isRefreshing = true
                GLog.d(TAG, "토큰 만료 신호 - ${Define.accessToken}")

                runCatching {
                    refresh()
                }.onSuccess {
                    val token = it.accessToken
                    Define.changeAccessToken(token)
                    tokenFlow.emit(token) // 새로운 토큰을 SharedFlow에 emit
                    GLog.d(TAG, "갱신 됨 - ${Define.accessToken}")
                }.onFailure {
                    _expireEffects.send(true)
                    isRefreshing = false
                    return block().copy(code = 200, message = "")
                }

                isRefreshing = false
            } else {
                GLog.d(TAG, "토큰 갱신 대기 중 - ${Define.accessToken}")
            }

            // SharedFlow에서 최신 토큰을 받아서 사용
//            val latestToken = tokenFlow.first() // 첫 번째 값을 받음 (이미 emit된 값)
//            DefineShared.userToken = latestToken
            return block.invoke()
        } else return response

//        return if (response.code == "E10005") {
//            if (!isRefreshing) {
//                isRefreshing = true
//                println("토큰 만료 신호, 갱신 중")
//                println("token 1 - ${Define.accessToken}")
//
////                delay(100)
//                // 토큰을 갱신하고 SharedFlow에 새 토큰 emit
//
//                runCatching {
//                    refresh()
//                }.onSuccess {
//                    val token = it.accessToken
//                    Define.accessToken = token
//                    tokenFlow.emit(token) // 새로운 토큰을 SharedFlow에 emit
//                    println("token 2 - ${Define.accessToken}")
//                }.onFailure {
//                    _expireEffects.send(true)
//                }
//
//                isRefreshing = false
//            } else {
//                println("토큰 갱신 대기 중")
//                println("token 3 - ${Define.accessToken}")
//            }
//
//            // SharedFlow에서 최신 토큰을 받아서 사용
////            val latestToken = tokenFlow.first() // 첫 번째 값을 받음 (이미 emit된 값)
////            DefineShared.userToken = latestToken
//
//            println("token 4 - ${Define.accessToken}")
//            block.invoke()
//        } else response
    }

    suspend fun refresh(): RefreshResponse {
        return handleApi(TAG) {
            authService.refresh(
                PlayerRequest(Define.playerId)
            )
        }.successOr(RefreshResponse())
    }

    fun callLogout() {
        scope.launch {
            runCatching {
                logoutUseCase.invoke()
            }.onSuccess { response ->
                Define.clearInfo()
            }.onFailure {
                Define.clearInfo()
                GLog.d(TAG, it.stackTraceToString())
            }
        }
    }
}