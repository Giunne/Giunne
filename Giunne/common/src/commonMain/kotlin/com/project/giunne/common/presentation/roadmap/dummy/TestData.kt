package com.project.giunne.common.presentation.roadmap.dummy

import com.project.giunne.common.presentation.roadmap.node.NodeStatus
import com.project.giunne.common.presentation.roadmap.state.ExerciseUiState
import com.project.giunne.common.presentation.roadmap.state.JoggingUiState

val exerciseList = listOf(
    ExerciseUiState(NodeStatus.LOCK, "0.CORE", "코어"),
    ExerciseUiState(NodeStatus.LOCK, "0.LOWER_BODY", "하체"),
    ExerciseUiState(NodeStatus.LOCK, "0.DEADLIFT", "데드리프트"),
    ExerciseUiState(NodeStatus.LOCK, "0.SQUATS", "스쿼트"),
    ExerciseUiState(NodeStatus.LOCK, "0.LUNGES", "런지"),
)

val joggingUiState = JoggingUiState(
    week = 5,
    progress = 1,
    bonusWeek = listOf(6, 12)
)