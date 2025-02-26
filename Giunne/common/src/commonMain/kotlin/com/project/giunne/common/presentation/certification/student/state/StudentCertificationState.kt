package com.project.giunne.common.presentation.certification.student.state

data class StudentCertificationState(
    val pageType: CertPage = CertPage.RoadMap,
    val roadmapCertConfirmDialog: Boolean = false,
    val runningCertConfirmDialog: Boolean = false
)
