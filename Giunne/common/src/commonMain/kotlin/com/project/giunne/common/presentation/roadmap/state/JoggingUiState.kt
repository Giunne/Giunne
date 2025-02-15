package com.project.giunne.common.presentation.roadmap.state

data class JoggingUiState(
    val week: Int,
    val progress: Int = 0,
    val bonusWeek: List<Int>
)