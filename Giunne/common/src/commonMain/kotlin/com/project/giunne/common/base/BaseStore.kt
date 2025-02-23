package com.project.giunne.common.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStore<UI_STATE>(
    initialState: UI_STATE,
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {

    var uiState = MutableStateFlow(initialState)
        private set

    private val currentState: UI_STATE
        get() = uiState.value

    // state update
    protected fun setState(
        reduce: UI_STATE.() -> UI_STATE
    ) {
        uiState.update { currentState.reduce() }
    }
}