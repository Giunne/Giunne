package util

import java.awt.Toolkit

fun getScreenDPI(): Float {
    val toolkit = Toolkit.getDefaultToolkit()
    val screenResolution = toolkit.screenResolution
    return screenResolution.toFloat() / 96f // 96 DPI가 기본 값
}

fun getScreenWidth(): Int {
    val toolkit = Toolkit.getDefaultToolkit()
    return toolkit.screenSize.width
}

fun getScreenHeight(): Int {
    val toolkit = Toolkit.getDefaultToolkit()
    return toolkit.screenSize.height
}