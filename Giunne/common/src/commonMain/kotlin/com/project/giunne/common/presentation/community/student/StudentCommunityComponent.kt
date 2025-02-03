package com.project.giunne.common.presentation.community.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentCommunityCompone"
class StudentCommunityComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        GLog.d(TAG, "onCreate")
    }
}