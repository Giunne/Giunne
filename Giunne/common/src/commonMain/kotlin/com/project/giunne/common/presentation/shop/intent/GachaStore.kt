package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.shop.dummy.gachaItems1
import com.project.giunne.common.presentation.shop.dummy.gachaItems2
import com.project.giunne.common.presentation.shop.state.GachaEvent
import com.project.giunne.common.presentation.shop.state.GachaState
import com.project.giunne.common.presentation.shop.state.RandomItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class GachaStore: BaseComponent<GachaState, GachaEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = GachaState()
) {
    fun getPickingRandomItem(): RandomItem {
        return RandomItem()
    }

    fun getPickingItemList(
        isAdvanced: Boolean
    ) {
        /*TODO(API 나오면 연결)*/
        if (isAdvanced) {
            setState {
                copy(
                    gachaCost = 300,
                    gachaItemList = gachaItems2
                )
            }
        } else {
            setState {
                copy(
                    gachaCost = 100,
                    gachaItemList = gachaItems1
                )
            }
        }
    }
}