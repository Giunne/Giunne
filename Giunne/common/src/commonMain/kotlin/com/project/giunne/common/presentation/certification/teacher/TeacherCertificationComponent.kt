package com.project.giunne.common.presentation.certification.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.certification.student.intent.TeacherCertificationEvent
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.student.state.TeacherCertificationState
import com.project.giunne.common.util.GLog
import org.koin.core.component.KoinComponent

private const val TAG = "TeacherCertificationComponent"
class TeacherCertificationComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<TeacherCertificationState, TeacherCertificationEvent>(initialState = TeacherCertificationState()) {

    fun onClickRoadmapTap() {
        setState{
            copy(pageType = CertPage.RoadMap)
        }
    }

    fun onClickRunningTap() {
        setState{
            copy(pageType = CertPage.Running)
        }
    }

    init {
        GLog.d(TAG, "onCreate")
    }
}