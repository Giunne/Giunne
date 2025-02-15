package com.project.giunne.common.presentation.roadmap.state

data class RoadMapUiState(
    val exerciseList: List<ExerciseUiState>,
    val joggingList: List<JoggingUiState>
)