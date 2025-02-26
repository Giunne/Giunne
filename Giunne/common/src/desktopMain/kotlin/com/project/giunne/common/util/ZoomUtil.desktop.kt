package com.project.giunne.common.util

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.zoomBy
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.sign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun Modifier.onZoomEvent(
    scope: CoroutineScope,
    state: TransformableState,
    onSingleTapEvent: (Offset) -> Unit
): Modifier {
    return this.onPointerEvent(
        eventType = PointerEventType.Scroll,
    ) {
        val change = it.changes.first()
        val delta = -change.scrollDelta.y.toInt().sign

        scope.launch {
            if (delta > 0) state.zoomBy(zoomFactor = 1.1f)
            else state.zoomBy(zoomFactor = 0.9f)
        }
    }.pointerInput(true) {
        var clickCnt = 0

        detectTapGestures(
            onTap = {
                onSingleTapEvent(it)
            },
            onDoubleTap = {
                scope.launch {
                    if (clickCnt++ < 2) {
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