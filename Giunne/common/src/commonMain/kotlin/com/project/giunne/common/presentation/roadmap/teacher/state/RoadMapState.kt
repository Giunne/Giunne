package com.project.giunne.common.presentation.roadmap.teacher.state

import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.util.DataThrowable

data class RoadMapState(
    val isLoading: Boolean = false,
    val roadMapInfo: List<RoadMapInfo> = listOf(),
    val courseMap: Map<Long, List<QuestInfo>> = mapOf(),
    val error: DataThrowable? = null
)

sealed interface RoadMapEvent {
    data class Success(val message: String): RoadMapEvent
}