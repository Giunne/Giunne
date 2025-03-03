package com.project.giunne.common.presentation.roadmap.state

import com.project.giunne.common.presentation.roadmap.node.NodeStatus

data class ExerciseUiState(
    val status: NodeStatus = NodeStatus.LOCK,
    val step: String = "",
    val name: String = "",
    val isBonusDay: Boolean = false,
    val imageUrl: String = "",
    val title: String = "1단계 로드맵",
    val description: String = "코어 힘을 기르는데\n" +
            "도움이 많이 되는 운동이에요.",
    val youtubeUrl: String = "https://www.youtube.com/",
    val exerciseStep: String = "1. 지면에 양쪽 손과 무릎을 몸과 일자로 위치시켜요.\n" +
            "2. 한쪽손을 앞으로 쭉 뻗고, 반대쪽 발을 뒤로 쭉 뻗어요.",
    val rewardExp: Int = 10,
    val rewardCoin: Int = 3,
    val nextExerciseTitle: String = "2단계 데드버그"
)