package com.project.giunne.common.presentation.roadmap.node

import androidx.compose.ui.geometry.Offset

fun roadMap1(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {
    var currentOffset = Offset(width / 2, height)

    val node = mutableListOf<Node>()
    repeat(5) { index ->
        val spacing = if (index == 0) 48f else 16f
        val boxSize = if (index == 0) 30f else 60f
        currentOffset = Offset(currentOffset.x, currentOffset.y - boxSize - spacing)
        node.add(Node(currentOffset, boxSize, spacing))

        // 가로 왼쪽 오른쪽 순으로 저장
        if (index == 4) {
            val leftNodeOffset = Offset(currentOffset.x - boxSize - spacing, currentOffset.y)
            val rightNodeOffset = Offset(currentOffset.x + boxSize + spacing, currentOffset.y)
            node.add(Node(leftNodeOffset, boxSize, spacing))
            node.add(Node(rightNodeOffset, boxSize, spacing))
        }
    }
    val endNode = Node(Offset(width / 2, 0f),80f, 0f)
    val connectList = buildList {
        add(ConnectNode(node[0], node[1], Connect.STRAIGHT))
        add(ConnectNode(node[1], node[2], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[3], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[4], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[5], Connect.TOP_CURVE))
        add(ConnectNode(node[3], node[6], Connect.TOP_CURVE))
        add(ConnectNode(node[3], endNode, Connect.STRAIGHT))
    }
    return Pair(node, connectList)
}

fun roadMap2(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {
    var currentOffset = Offset(width / 2, height - 32)

    val node = mutableListOf<Node>()
    val boxSize = 60f
    val spacing = 16f
    repeat(5) { index ->
        currentOffset = Offset(currentOffset.x, currentOffset.y - boxSize - spacing)
        if (index < 3) {
            val leftNodeOffset = Offset(currentOffset.x - boxSize - spacing, currentOffset.y)
            val rightNodeOffset = Offset(currentOffset.x + boxSize + spacing, currentOffset.y)
            // 가운데, 왼쪽, 오른쪽 순으로 배치
            node.add(Node(currentOffset, boxSize, spacing))
            node.add(Node(leftNodeOffset, boxSize, spacing))
            node.add(Node(rightNodeOffset, boxSize, spacing))
        } else {
            // 위로만 쌓은
            node.add(Node(currentOffset, boxSize, spacing))
        }
    }
    val startNode = Node(Offset(width / 2, height - 32),60f, 0f)
    val emptyNode = Node(Offset(width / 2, height),60f, 0f)
    val endNode = Node(Offset(width / 2, 0f),60f, 0f)
    val connectList = buildList {
        add(ConnectNode(emptyNode, startNode, Connect.STRAIGHT))
        add(ConnectNode(startNode, node[0], Connect.STRAIGHT))
        add(ConnectNode(startNode, node[1], Connect.TOP_CURVE))
        add(ConnectNode(startNode, node[2], Connect.TOP_CURVE))
        add(ConnectNode(node[1], node[4], Connect.STRAIGHT))
        add(ConnectNode(node[4], node[7], Connect.STRAIGHT))
        add(ConnectNode(node[7], node[9], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[0], node[3], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[6], Connect.STRAIGHT))
        add(ConnectNode(node[6], node[9], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[5], Connect.STRAIGHT))
        add(ConnectNode(node[5], node[8], Connect.STRAIGHT))
        add(ConnectNode(node[8], node[9], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[9], node[10], Connect.STRAIGHT))
        add(ConnectNode(node[10], endNode, Connect.STRAIGHT))
    }
    return Pair(node, connectList)
}

fun roadMap3(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {

    val spacing = 32f
    val boxSize = 60f
    val node = mutableListOf<Node>()
    var currentOffset = Offset(width / 2, height)

    repeat(5) { index ->
        currentOffset = Offset(currentOffset.x, currentOffset.y - boxSize - spacing)
        // 가로 두개
        if (index == 3) {
            val leftOffset = Offset(currentOffset.x - boxSize, currentOffset.y)
            val rightOffset = Offset(currentOffset.x + boxSize, currentOffset.y)
            node.add(Node(leftOffset, boxSize, spacing))
            node.add(Node(rightOffset, boxSize, spacing))
        } else {
            node.add(Node(currentOffset, boxSize, spacing))
        }
    }
    val startNode = Node(Offset(width / 2, height),60f, 0f)
    val connectList = buildList {
        add(ConnectNode(startNode, node[0], Connect.STRAIGHT))
        add(ConnectNode(node[0], node[1], Connect.STRAIGHT))
        add(ConnectNode(node[1], node[2], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[3], Connect.TOP_CURVE))
        add(ConnectNode(node[2], node[4], Connect.TOP_CURVE))
        add(ConnectNode(node[3], node[5], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[4], node[5], Connect.BOTTOM_CURVE))
    }
    return Pair(node, connectList)
}

fun roadMap4(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {

    val spacing = 16f
    val boxSize = 60f
    val left = Node(Offset(width / 2 - width / 3, 0f),60f, 0f)
    val center = Node(Offset(width / 2, 0f),60f, 0f)
    val right = Node(Offset(width / 2 + width / 3, 0f),60f, 0f)

    val startNode1 = Node(Offset(width / 2, height - 48f), 30f, 0f)

    val node = mutableListOf(startNode1)
    var leftOffset = Offset(width / 3, height - 48f)
    var rightOffset = Offset(width - width / 3, height - 48f)

    // 4-2 첫 번째 로드맵
    repeat(2) {
        leftOffset = Offset(leftOffset.x, leftOffset.y - boxSize - spacing)
        rightOffset = Offset(rightOffset.x, rightOffset.y - boxSize - spacing)
        node.add(Node(leftOffset, boxSize, spacing))
        node.add(Node(rightOffset, boxSize, spacing))
    }
    // 4-2 두 번째 로드맵
    var startOffsetY = rightOffset.y - boxSize * 2
    val startNode2 = Node(Offset(width / 2, startOffsetY), 30f, 0f)
    leftOffset = Offset(width / 3, startOffsetY)
    rightOffset = Offset(width - width / 3, startOffsetY)
    node.add(startNode2)
    repeat(2) { index ->
        if (index == 0) {
            leftOffset = Offset(leftOffset.x, leftOffset.y - boxSize - spacing)
            rightOffset = Offset(rightOffset.x, rightOffset.y - boxSize - spacing)
        } else {
            val centerNode = Offset(center.centerOffset.x,leftOffset.y - boxSize - spacing)
            leftOffset = Offset(left.centerOffset.x, leftOffset.y - (boxSize - spacing) * 2)
            rightOffset = Offset(right.centerOffset.x, rightOffset.y - boxSize - spacing)
            node.add(Node(centerNode, boxSize, spacing))
        }
        node.add(Node(leftOffset, boxSize, spacing))
        node.add(Node(rightOffset, boxSize, spacing))
    }
    val lastOffset = Offset(width - width / 3,leftOffset.y - boxSize - spacing)
    node.add(Node(lastOffset, boxSize, spacing))

    val connectList = buildList {
        // 4-2 첫 번째 로드맵
        add(ConnectNode(node[0], node[1], Connect.TOP_CURVE))
        add(ConnectNode(node[0], node[2], Connect.TOP_CURVE))
        add(ConnectNode(node[1], node[3], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[4], Connect.STRAIGHT))
        // 4-2 두 번째 로드맵
        add(ConnectNode(node[5], node[6], Connect.TOP_CURVE))
        add(ConnectNode(node[5], node[7], Connect.TOP_CURVE))
        add(ConnectNode(node[6], node[9], Connect.TOP_CURVE))
        add(ConnectNode(node[7], node[8], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[7], node[10], Connect.TOP_CURVE))
        add(ConnectNode(node[8], node[11], Connect.TOP_CURVE))
        add(ConnectNode(node[10], node[11], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[8], center, Connect.BOTTOM_CURVE))
        add(ConnectNode(node[9], left, Connect.STRAIGHT))
        add(ConnectNode(node[11], right, Connect.TOP_CURVE))

    }

    return Pair(node, connectList)
}

fun roadMap5(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {

    val spacing = 16f
    val boxSize = 60f
    var currentOffset = Offset(width / 2, height)
    val node = mutableListOf<Node>()
    val left = Node(Offset(width / 2 - width / 3, height),60f, 0f)
    val center = Node(Offset(width / 2, height),60f, 0f)
    val right = Node(Offset(width / 2 + width / 3, height),60f, 0f)

    repeat(5) { index ->
        currentOffset = Offset(currentOffset.x, currentOffset.y - boxSize - spacing)
        val leftNodeOffset = Offset(currentOffset.x - width / 3, currentOffset.y)
        val rightNodeOffset = Offset(currentOffset.x + width / 3, currentOffset.y)
        if (index < 3) {
            // 가운데, 왼쪽, 오른쪽 순으로 배치
            node.add(Node(currentOffset, boxSize, spacing))
            node.add(Node(leftNodeOffset, boxSize, spacing))
            node.add(Node(rightNodeOffset, boxSize, spacing))
        } else {
            node.add(Node(rightNodeOffset, boxSize, spacing))
        }
    }

    val connectList = buildList {
        add(ConnectNode(left, node[1], Connect.STRAIGHT))
        add(ConnectNode(node[1], node[4], Connect.STRAIGHT))
        add(ConnectNode(node[4], node[7], Connect.STRAIGHT))
        add(ConnectNode(center, node[0], Connect.STRAIGHT))
        add(ConnectNode(node[0], node[3], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[6], Connect.STRAIGHT))
        add(ConnectNode(right, node[2], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[5], Connect.STRAIGHT))
        add(ConnectNode(node[5], node[8], Connect.STRAIGHT))
        add(ConnectNode(node[8], node[9], Connect.STRAIGHT))
        add(ConnectNode(node[9], node[10], Connect.STRAIGHT))
    }

    return Pair(node, connectList)
}