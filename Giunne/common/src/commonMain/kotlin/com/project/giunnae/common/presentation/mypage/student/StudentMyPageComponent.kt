package com.project.giunnae.common.presentation.mypage.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunnae.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentMyPageComponent"
class StudentMyPageComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        GLog.d(TAG, "onCreate")
    }
}