package com.project.giunne.common.presentation.roadmap.state

import com.project.giunne.common.presentation.roadmap.node.NodeStatus

data class ExerciseUiState(
    val status: NodeStatus,
    val step: String,
    val name: String,
    val isBonusDay: Boolean = false,
    val imageUrl: String = ""
)