package com.project.giunne.common.presentation.community.student.state

data class StudentCommunityState(
    val datePriority: DatePriority = DatePriority.NEWEST,
    val datePrioritySelectDialog: Boolean = false,
    val roadmapFilter: String = "전체",
    val runningFilter: String = "전체",
    val roadmapFilterDialog: Boolean = false,
    val runningFilterDialog: Boolean = false,
)
