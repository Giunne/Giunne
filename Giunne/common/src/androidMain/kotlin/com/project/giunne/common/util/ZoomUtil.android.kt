package com.project.giunne.common.util

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
actual fun Modifier.onZoomEvent(
    scope: CoroutineScope,
    state: TransformableState,
    onSingleTapEvent: (Offset) -> Unit
): Modifier {
    return this.pointerInput(true) {
        var clickCnt = 0

        detectTapGestures(
            onTap = {
                onSingleTapEvent(it)
            },
            onDoubleTap = {
                scope.launch {
                    if (clickCnt++ < 2){
                        state.animateZoomBy(1.4f)
                    }
                    else {
                        state.animateZoomBy(0.5f)
                        clickCnt = 0
                    }
                }
            }
        )
    }
}