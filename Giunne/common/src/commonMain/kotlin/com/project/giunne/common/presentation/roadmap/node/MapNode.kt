package com.project.giunne.common.presentation.roadmap.node

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.project.giunne.common.ui.theme.GPColor

enum class NodeStatus {
    LOCK, UNCHECK, CHECK, UPLOAD, CONFIRM
}

enum class Connect {
    TOP_CURVE, STRAIGHT, BOTTOM_CURVE,
}

data class Node(
    val centerOffset: Offset,
    val boxSize: Float,
    val spacing: Float,
    val status: NodeStatus = NodeStatus.LOCK,
    val step: String = ""
) {
    val drawOffset: Offset
        get() = Offset(centerOffset.x - boxSize / 2, centerOffset.y - boxSize / 2)
}

data class ConnectNode(
    val from: Node,
    val to: Node,
    val connect: Connect = Connect.STRAIGHT
) {
    val controlPoint: Offset
        get() {
            return when (connect) {
                Connect.TOP_CURVE -> Offset(to.centerOffset.x, from.centerOffset.y)
                Connect.BOTTOM_CURVE -> Offset(from.centerOffset.x, to.centerOffset.y)
                else -> throw IllegalArgumentException("Connect 방향이 잘못되었습니다.")
            }
        }
}