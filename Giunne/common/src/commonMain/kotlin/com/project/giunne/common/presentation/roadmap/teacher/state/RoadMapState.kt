package com.project.giunne.common.presentation.roadmap.teacher.state

import com.project.giunne.common.data.remote.response.CourseInfo
import com.project.giunne.common.data.remote.response.QuestStateInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.util.DataThrowable

data class RoadMapState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val modifySuccess: Boolean = false,
    val roadMapInfo: List<RoadMapInfo> = listOf(),
    val studentList: List<QuestStateInfo> = listOf(),
    val checkedIdSet: Set<Int> = hashSetOf(),
    val alreadyCheckedStudentSet: Set<String> = hashSetOf(),
    val courseMap: Map<Long, List<CourseInfo>> = mapOf(),
    val error: DataThrowable? = null
)

sealed interface RoadMapEvent