package com.project.giunne.common.presentation.certification.student.state

import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.DataThrowable

data class StudentCertificationState(
    val pageType: CertPage = CertPage.RoadMap,
    val roadmapCertConfirmDialog: Boolean = false,
    val runningCertConfirmDialog: Boolean = false,

    val loading: Boolean = false,

    val roadmapProgressList: List<StudentQuestInfo> = listOf(),
    val runningProgressList: List<StudentQuestInfo> = listOf(),

    val roadmapHistoryList: List<StudentQuestInfo> = listOf(),
    val runningHistoryList: List<StudentQuestInfo> = listOf(),

    val error: DataThrowable? = null,
    val fileError: Boolean = false
)
