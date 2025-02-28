package com.project.giunne.common.util

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Constraints
import com.project.giunne.common.base.BaseStore
import kotlinx.coroutines.CoroutineScope

@Composable
expect fun Modifier.onZoomEvent(
    scope: CoroutineScope,
    state: TransformableState,
    onSingleTapEvent: (Offset) -> Unit
): Modifier

class ZoomStore : BaseStore<ZoomUiState>(ZoomUiState()) {

    /**
     * 이미지의 Zoom 상태 값 관리
     *
     * @param zoomChange : 확대 크기
     * @param panChangeX : OffsetX
     * @param panChangeY : OffsetY
     * @param maxWidth : Zoom을 사용하는 Composable 최대 넓이
     * @param maxHeight : Zoom을 사용하는 Composable 최대 높이
     * @see rememberZoomState
     */
    fun onUpdateZoom(
        zoomChange: Float,
        panChangeX: Float,
        panChangeY: Float,
        maxWidth: Float,
        maxHeight: Float
    ) {
        setState {
            val extraWidth = (scale - 1) * maxWidth
            val extraHeight = (scale - 1) * maxHeight

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            copy(
                scale = (scale * zoomChange).coerceIn(MIN_SCALE, MAX_SCALE),
                offsetX = (offsetX + (panChangeX * scale)).coerceIn(-maxX, maxX),
                offsetY = (offsetY + (panChangeY * scale)).coerceIn(-maxY, maxY)
            )
        }
    }

    fun onResetZoom() {
        setState {
            copy(
                scale = MIN_SCALE,
                offsetX = OFFSET_X,
                offsetY = OFFSET_Y
            )
        }
    }

    companion object {
        const val MIN_SCALE: Float = 1f
        const val MAX_SCALE: Float = 2f
        const val OFFSET_X: Float = 0f
        const val OFFSET_Y: Float = 0f
    }
}

@Composable
fun rememberZoomState(
    zoomStore: ZoomStore,
    constraints: Constraints
): TransformableState = rememberTransformableState { zoomChange, panChange, _ ->
    zoomStore.onUpdateZoom(
        zoomChange = zoomChange,
        panChangeX = panChange.x,
        panChangeY = panChange.y,
        maxWidth = constraints.maxWidth.toFloat(),
        maxHeight = constraints.maxHeight.toFloat()
    )
}

data class ZoomUiState(
    val scale: Float = 1f,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f
)