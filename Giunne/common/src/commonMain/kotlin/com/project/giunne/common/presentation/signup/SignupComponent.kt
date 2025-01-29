package com.project.giunne.common.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "SignupComponent"
class SignupComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext {
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        GLog.d(TAG, "onCreate")
    }

    companion object {
        const val TYPE_NONE = ""
        const val TYPE_TEACHER = "teacher"
        const val TYPE_STUDENT = "student"
    }
}