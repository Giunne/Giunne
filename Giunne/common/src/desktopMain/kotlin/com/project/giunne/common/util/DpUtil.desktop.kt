package com.project.giunne.common.util

import java.awt.Toolkit

actual object DpUtil {
    actual var res: Any? = null

    private val systemWidth: Int = Toolkit.getDefaultToolkit().screenSize.width
    private val systemHeight: Int = Toolkit.getDefaultToolkit().screenSize.height

    actual var sw: Int = 0
    actual fun convert(num: Int): Float {
//        return num.toFloat() * (0.57f * (systemHeight.toFloat() / 1080f))
//        return num.toFloat() * (0.57f * (systemWidth.toFloat() / 1920f))
        return num.toFloat() * ((systemHeight.toFloat() / 1080f))
    }

    actual fun convert(num: Float): Float {
        return num * ((systemHeight.toFloat() / 1080f))
    }

    actual fun wConvert(num: Int): Float {
//        return num.toFloat() * (0.76f * (systemHeight.toFloat() / 1080f))
//        return num.toFloat() * (0.76f * (systemWidth.toFloat() / 1920f))
        return num.toFloat() * ((systemWidth.toFloat() / 1920f))
    }
}