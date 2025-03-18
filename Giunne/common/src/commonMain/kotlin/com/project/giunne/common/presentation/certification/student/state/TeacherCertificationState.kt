package com.project.giunne.common.presentation.certification.student.state

import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.util.DataThrowable

data class TeacherCertificationState(
    val pageType: CertPage = CertPage.RoadMap,

    val uploadList: List<QuestUploadInfo> = listOf(),

    val loading: Boolean = false,
    val error: DataThrowable? = null
)
