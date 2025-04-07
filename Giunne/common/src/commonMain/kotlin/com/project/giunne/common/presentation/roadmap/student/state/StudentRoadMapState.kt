package com.project.giunne.common.presentation.roadmap.student.state

import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.data.util.DataThrowable

data class StudentRoadMapState(
    val isLoading: Boolean = false,
    val roadMapInfo: List<RoadMapInfo> = listOf(),
    val courseMap: Map<Long, List<StudentCourseInfo>> = mapOf(),
    val showSuccessExerciseDialog: Boolean = false,
    val showSuccessJoggingDialog: Boolean = false,
    val exerciseQuestName: String = "",
    val joggingQuestName: String = "",
    val error: DataThrowable? = null
)

sealed interface StudentRoadMapEvent