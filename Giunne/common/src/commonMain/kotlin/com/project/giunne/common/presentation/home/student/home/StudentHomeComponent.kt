package com.project.giunne.common.presentation.home.student.home

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentHomeComponent"
class StudentHomeComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        GLog.d(TAG, "onCreate")
    }
}