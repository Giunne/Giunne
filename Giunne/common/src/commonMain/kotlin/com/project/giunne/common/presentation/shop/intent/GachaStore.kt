package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.GachaRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.shop.GetGachaTypeUseCase
import com.project.giunne.common.domain.usecase.shop.PostGachaUseCase
import com.project.giunne.common.presentation.shop.state.GachaEvent
import com.project.giunne.common.presentation.shop.state.GachaState
import com.project.giunne.common.presentation.shop.state.RandomItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class GachaStore(
    private val getGachaTypeUseCase: GetGachaTypeUseCase = KoinJavaComponent.get(GetGachaTypeUseCase::class.java),
    private val postGachaUseCase: PostGachaUseCase = KoinJavaComponent.get(PostGachaUseCase::class.java)
): BaseComponent<GachaState, GachaEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = GachaState()
) {
    fun getGachaType() {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getGachaTypeUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        generalGachaInfo = response[0],
                        premiumGachaInfo = response[1],
                    )
                }
            }
            .onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
        }
    }

    suspend fun postGacha(
        gachaType: String
    ) {
//        scope.launch {
//            setState { copy(loading = true) }
            runCatching {
                postGachaUseCase(gachaRequest = GachaRequest(gachaType))
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        randomItem = response
                    )
                }
            }
            .onFailure {
                setState { copy(loading = false, error = it.asDataThrowable()) }
            }
//        }
    }
}