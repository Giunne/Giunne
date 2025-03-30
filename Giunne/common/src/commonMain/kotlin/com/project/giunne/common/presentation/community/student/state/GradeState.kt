package com.project.giunne.common.presentation.community.student.state

import com.project.giunne.common.data.util.DataThrowable

data class GradeState(
    val loading: Boolean = false,
    val successDialog: Boolean = false,
    val hasExtraPoints: Boolean = false,
    val isPass: Boolean = false,
    val starPoint: Int = 0,
    val gradeDialog: Boolean = false,
    val confirmDialog: Boolean = false,
    val error: DataThrowable? = null
)
