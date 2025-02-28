package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.domain.usecase.shop.GetGachaTypeUseCase
import com.project.giunne.common.presentation.shop.state.GachaEvent
import com.project.giunne.common.presentation.shop.state.GachaState
import com.project.giunne.common.presentation.shop.state.RandomItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class GachaStore(
    private val getGachaTypeUseCase: GetGachaTypeUseCase = KoinJavaComponent.get(GetGachaTypeUseCase::class.java)
): BaseComponent<GachaState, GachaEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = GachaState()
) {

    fun getGachaType() {
        scope.launch {
            runCatching {
                getGachaTypeUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        gachaInfo = response
                    )
                }
            }
            .onFailure {

            }
        }
    }
}