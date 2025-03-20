package com.project.giunne.common.presentation.roadmap.node

import androidx.compose.ui.geometry.Offset

fun roadMap1(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {
    val steps = listOf("1.CORE", "2.CORE", "3.CORE", "4-b.CORE", "4-a.CORE", "4-c.CORE")
    var currentOffset = Offset(width / 2, height - 78f)

    val node = mutableListOf<Node>()
    val spacing = 16f
    val boxSize =  60f
    node.add(Node(currentOffset, 30f, 48f, step = "0.CORE"))
    repeat(4) { index ->
        currentOffset = Offset(currentOffset.x, currentOffset.y - boxSize - spacing)
        node.add(Node(currentOffset, boxSize, spacing, step = steps[index]))

        // 가로 왼쪽 오른쪽 순으로 저장
        if (index == 3) {
            val leftNodeOffset = Offset(currentOffset.x - boxSize - spacing, currentOffset.y)
            val rightNodeOffset = Offset(currentOffset.x + boxSize + spacing, currentOffset.y)
            node.add(Node(leftNodeOffset, boxSize, spacing, step = steps[index + 1]))
            node.add(Node(rightNodeOffset, boxSize, spacing, step = steps[index + 2]))
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
        add(ConnectNode(node[4], endNode, Connect.STRAIGHT))
    }
    return Pair(node, connectList)
}

fun roadMap2(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {
    val steps = listOf("5-b.CORE", "6-b.CORE", "7-b.CORE", "5-a.CORE", "6-a.CORE", "7-a.CORE", "5-c.CORE", "6-c.CORE", "7-c.CORE", "8.CORE", "9.CORE")
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
            node.add(Node(currentOffset, boxSize, spacing, step = steps[index]))
            node.add(Node(leftNodeOffset, boxSize, spacing, step = steps[index + 3]))
            node.add(Node(rightNodeOffset, boxSize, spacing, step = steps[index + 6]))
        } else {
            // 위로만 쌓은
            node.add(Node(currentOffset, boxSize, spacing, step = steps[index + 6]))
        }
    }
    val startNode = Node(Offset(width / 2, height - 32),60f, 0f, step = "4-b.CORE")
    val emptyNode = Node(Offset(width / 2, height),60f, 0f, step = "4-b.CORE")
    val endNode = Node(Offset(width / 2, 0f),60f, 0f, step = "9.CORE")
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
    val steps = listOf("10.CORE", "11.CORE", "12.CORE", "13-a.CORE", "13-b.CORE", "14.CORE")
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
            node.add(Node(leftOffset, boxSize, spacing, step = steps[index]))
            node.add(Node(rightOffset, boxSize, spacing, step = steps[index + 1]))
        } else if (index == 4) {
            node.add(Node(currentOffset, boxSize, spacing, step = steps[index + 1]))
        } else {
            node.add(Node(currentOffset, boxSize, spacing, step = steps[index]))
        }
    }
    val startNode = Node(Offset(width / 2, height),60f, 0f, step = "9.CORE")
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
    val steps1 = listOf("1-a.LOWER_BODY", "2-a.LOWER_BODY", "1-b.LOWER_BODY", "2-b.LOWER_BODY", "3.LOWER_BODY")
    val spacing = 16f
    val boxSize = 60f

    val startNode1 = Node(Offset(width / 2, height - 48f), 30f, 0f, step = "0.LOWER_BODY")

    val node = mutableListOf(startNode1)
    var leftOffset = Offset(width / 3, height - 48f)
    var rightOffset = Offset(width - width / 3, height - 48f)

    // 4-2 첫 번째 로드맵
    repeat(2) { index ->
        leftOffset = Offset(leftOffset.x, leftOffset.y - boxSize - spacing)
        rightOffset = Offset(rightOffset.x, rightOffset.y - boxSize - spacing)
        node.add(Node(leftOffset, boxSize, spacing, step = steps1[index]))
        node.add(Node(rightOffset, boxSize, spacing, step = steps1[index + 2]))
        if (index == 1) {
            // 마지막 노드 추가
            val mid = Offset(width / 2, rightOffset.y - boxSize - spacing)
            node.add(Node(mid, boxSize, spacing, step = steps1[4]))
        }
    }
    val steps2 = listOf("1.SQUATS", "1.LUNGES", "1-a.DEADLIFT", "1-b.DEADLIFT", "2.DEADLIFT")
    // 4-2 두 번째 로드맵
    val startOffsetY = rightOffset.y - boxSize * 2
    var centerOffset = Offset(width / 2, startOffsetY - (boxSize - spacing) * 3)
    leftOffset = Offset(width / 2 - width / 3, startOffsetY - boxSize - spacing)
    rightOffset = Offset(width / 2 + width / 3, startOffsetY - boxSize - spacing)
    // 스쿼트
    val startNodeSquat = Node(leftOffset, 30f, 0f, step = "0.SQUATS")
    node.add(startNodeSquat)
    leftOffset = Offset(leftOffset.x, leftOffset.y - boxSize - spacing)
    node.add(Node(leftOffset, boxSize, spacing, step = steps2[0]))
    // 런지
    val startNodeLunge = Node(centerOffset, 30f, 0f, step = "0.LUNGES")
    node.add(startNodeLunge)
    centerOffset = Offset(centerOffset.x, centerOffset.y - boxSize - spacing)
    node.add(Node(centerOffset, boxSize, spacing, step = steps2[1]))
    // 데드
    val startNodeDeadlift = Node(rightOffset, 30f, 0f, step = "0.DEADLIFT")
    node.add(startNodeDeadlift)
    val leftDead = Offset(rightOffset.x - 60f, rightOffset.y - boxSize - spacing)
    val rightDead = Offset(rightOffset.x + 30f, rightOffset.y - boxSize - spacing)
    node.add(Node(leftDead, boxSize, spacing, step = steps2[2]))
    node.add(Node(rightDead, boxSize, spacing, step = steps2[3]))
    val lastDead = Offset(rightOffset.x, leftDead.y - boxSize - spacing)
    node.add(Node(lastDead, boxSize, spacing, step = steps2[4]))


    // 연결 될 상단 offset
    val left = Node(Offset(width / 2 - width / 3, 0f),60f, 0f, step = "1.SQUATS")
    val center = Node(Offset(width / 2, 0f),60f, 0f, step = "1.LUNGES")
    val right = Node(Offset(width / 2 + width / 3, 0f),60f, 0f, step = "2.DEADLIFT")

    val connectList = buildList {
        // 4-2 첫 번째 로드맵
        add(ConnectNode(node[0], node[1], Connect.TOP_CURVE))
        add(ConnectNode(node[0], node[2], Connect.TOP_CURVE))
        add(ConnectNode(node[1], node[3], Connect.STRAIGHT))
        add(ConnectNode(node[2], node[4], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[5], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[4], node[5], Connect.BOTTOM_CURVE))
        // 4-2 두 번째 로드맵
        add(ConnectNode(node[5], node[6], Connect.TOP_CURVE))
        add(ConnectNode(node[5], node[10], Connect.TOP_CURVE))
        add(ConnectNode(node[6], node[7], Connect.STRAIGHT))
        add(ConnectNode(node[7], left, Connect.STRAIGHT))
        add(ConnectNode(node[8], node[7], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[8], node[9], Connect.STRAIGHT))
        add(ConnectNode(node[9], center, Connect.STRAIGHT))
        add(ConnectNode(node[10], node[11], Connect.TOP_CURVE))
        add(ConnectNode(node[10], node[12], Connect.TOP_CURVE))
        add(ConnectNode(node[11], node[13], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[12], node[13], Connect.BOTTOM_CURVE))
        add(ConnectNode(node[13], right, Connect.STRAIGHT))
    }

    return Pair(node, connectList)
}

fun roadMap5(
    width: Float,
    height: Float
): Pair<List<Node>, List<ConnectNode>> {
    val steps = listOf("2.LUNGES", "3.LUNGES", "4.LUNGES", "2.SQUATS", "3.SQUATS", "4-a.SQUATS", "4-b.SQUATS", "3.DEADLIFT", "4.DEADLIFT", "5.DEADLIFT", "6.DEADLIFT", "7.DEADLIFT")
    val spacing = 16f
    val boxSize = 60f
    val currentOffset = Offset(width / 2, height)
    var midOffset = currentOffset
    var leftNodeOffset = Offset(currentOffset.x - width / 3, currentOffset.y)
    var rightNodeOffset = Offset(currentOffset.x + width / 3, currentOffset.y)
    val node = mutableListOf<Node>()
    val left = Node(Offset(width / 2 - width / 3, height),60f, 0f, step = "1.SQUATS")
    val center = Node(Offset(width / 2, height),60f, 0f, step = "1.LUNGES")
    val right = Node(Offset(width / 2 + width / 3, height),60f, 0f, step = "2.DEADLIFT")

    // 가운데
    for (index in 0..2) {
        midOffset = Offset(midOffset.x, midOffset.y - boxSize - spacing)
        node.add(Node(midOffset, boxSize, spacing, step = steps[index]))
    }

    // 왼쪽
    for (index in 3..4) {
        leftNodeOffset = Offset(leftNodeOffset.x, leftNodeOffset.y - boxSize - spacing)
        node.add(Node(leftNodeOffset, boxSize, spacing, step = steps[index]))
        if (index == 4) {
            // 양 쪽 두개
            val left = Offset(leftNodeOffset.x - 40, leftNodeOffset.y - boxSize - spacing)
            val right = Offset(leftNodeOffset.x + 40, leftNodeOffset.y - boxSize - spacing)
            node.add(Node(left, boxSize, spacing, step = steps[5]))
            node.add(Node(right, boxSize, spacing, step = steps[6]))
        }
    }

    // 오른쪽
    for (index in 7..11) {
        rightNodeOffset = Offset(rightNodeOffset.x, rightNodeOffset.y - boxSize - spacing)
        node.add(Node(rightNodeOffset, boxSize, spacing, step = steps[index]))
    }

    val connectList = buildList {
        add(ConnectNode(center, node[0], Connect.STRAIGHT))
        add(ConnectNode(node[0], node[1], Connect.STRAIGHT))
        add(ConnectNode(node[1], node[2], Connect.STRAIGHT))
        add(ConnectNode(left, node[3], Connect.STRAIGHT))
        add(ConnectNode(node[3], node[4], Connect.STRAIGHT))
        add(ConnectNode(node[4], node[5], Connect.TOP_CURVE))
        add(ConnectNode(node[4], node[6], Connect.TOP_CURVE))
        add(ConnectNode(right, node[7], Connect.STRAIGHT))
        add(ConnectNode(node[7], node[8], Connect.STRAIGHT))
        add(ConnectNode(node[8], node[9], Connect.STRAIGHT))
        add(ConnectNode(node[9], node[10], Connect.STRAIGHT))
        add(ConnectNode(node[10], node[11], Connect.STRAIGHT))
    }

    return Pair(node, connectList)
}