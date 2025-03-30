package com.project.giunne.common.util

import android.content.Context
import android.content.res.Resources

actual object DpUtil {
    actual var res: Any? = null

    actual var sw: Int = 0
    fun initDesplayMetrics(context: Context) {
        res = context.resources
        sw = (res as Resources).configuration.smallestScreenWidthDp
    }

    /* 비율이 다른 기기 추가시 추가 */
    actual fun convert(num: Int): Float {
        return if (sw <= 360) { num * 1.00f }
        else if (sw <= 420) { num * 1.13f }
        else { num * 1.13f }
    }

    actual fun convert(num: Float): Float {
        return if (sw <= 360) { num * 1.00f }
        else if (sw <= 420) { num * 1.13f }
        else { num * 1.13f }
    }

    actual fun wConvert(num: Int): Float {
        return if (sw <= 360) { num * 1.00f }
        else if (sw <= 420) { num * 1.13f }
        else { num * 1.13f }
    }
}