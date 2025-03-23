package com.project.giunne.common.presentation.community.student.state

import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.QuestTypeInfo
import com.project.giunne.common.data.remote.response.StudentPostingInfo
import com.project.giunne.common.data.util.DataThrowable

data class TeacherCommunityState(
    val datePriority: DatePriority = DatePriority.NEWEST,
    val datePrioritySelectDialog: Boolean = false,
    val roadmapFilter: String = "전체",
    val runningFilter: String = "전체",
    val roadmapFilterDialog: Boolean = false,
    val runningFilterDialog: Boolean = false,

    val postingList: List<StudentPostingInfo> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),
    val questTypeList: List<QuestTypeInfo> = listOf(),

    val loading: Boolean = false,
    val error: DataThrowable? = null
)
