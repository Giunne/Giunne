package com.project.giunne.common.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

expect object DpUtil {
    var res: Any?

    var sw: Int
    fun convert(num: Int): Float
    fun convert(num: Float): Float
    fun wConvert(num: Int): Float
}

@Stable
inline val Int.gdp: Dp
    get() = Dp(
    value = DpUtil.convert(this)
)

@Stable
inline val Float.gdp: Dp
    get() = Dp(
        value = DpUtil.convert(this)
    )

@Stable
inline val Int.wGdp: Dp
    get() = Dp(
    value = DpUtil.wConvert(this)
)

@Stable
inline val Int.gsp: TextUnit get() = this.gdp.value.sp