package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.domain.usecase.shop.GetGachaMapUseCase
import com.project.giunne.common.presentation.shop.dummy.gachaItems1
import com.project.giunne.common.presentation.shop.dummy.gachaItems2
import com.project.giunne.common.presentation.shop.state.GachaEvent
import com.project.giunne.common.presentation.shop.state.GachaState
import com.project.giunne.common.presentation.shop.state.RandomItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

class GachaStore(
    private val getGachaMapUseCase: GetGachaMapUseCase = KoinJavaComponent.get(GetGachaMapUseCase::class.java)
): BaseComponent<GachaState, GachaEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = GachaState()
) {

    fun getPickingRandomItem(): RandomItem {
        return RandomItem()
    }

    fun getPickingItemList() {
        scope.launch {
            runCatching {
                getGachaMapUseCase()
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