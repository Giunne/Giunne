package com.project.giunne.common.presentation.roadmap.dummy

import com.project.giunne.common.presentation.roadmap.node.NodeStatus
import com.project.giunne.common.presentation.roadmap.state.ExerciseUiState
import com.project.giunne.common.presentation.roadmap.state.JoggingUiState

val exerciseList = listOf(
    ExerciseUiState(NodeStatus.LOCK, "1", "스쿼트"),
    ExerciseUiState(NodeStatus.LOCK, "2", "스쿼트"),
    ExerciseUiState(NodeStatus.LOCK, "3", "스쿼트"),
    ExerciseUiState(NodeStatus.UNCHECK, "4-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "4-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "4-c", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "5-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "5-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "5-c", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "6-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "6-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "6-c", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "7-a", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "7-b", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "7-c", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "8", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "9", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "10", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "11", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "12", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "13-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "13-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "14", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "15-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "15-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "16-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "16-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "17", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "18", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "19", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "20", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "21", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "22", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "23", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "24", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "25", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "26-a", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "26-b", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "27", "스쿼트", true),
    ExerciseUiState(NodeStatus.CONFIRM, "28", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "29", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "30", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "31", "스쿼트"),
    ExerciseUiState(NodeStatus.CONFIRM, "32", "스쿼트", true),
)

val joggingUiState = JoggingUiState(
    week = 5,
    progress = 1,
    bonusWeek = listOf(6, 12)
)