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
//        return if (sw <= 720) { num * 0.43f } // 최소 너비 720 이하일 때
//        else { num * 0.4715f } // 720 초과
        return if (sw <= 720) { num * 1.13f } // 최소 너비 720 이하일 때
        else { num * 1.15f } // 720 초과
    }

    actual fun wConvert(num: Int): Float {
        return if (sw <= 720) { num * 0.43f } // 최소 너비 720 이하일 때
        else { num * 0.4715f } // 720 초과
    }
}