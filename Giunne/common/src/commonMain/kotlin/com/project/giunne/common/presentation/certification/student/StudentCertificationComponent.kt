package com.project.giunne.common.presentation.certification.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.certification.student.intent.StudentCertificationEvent
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.student.state.StudentCertificationState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentCertificationComponent"
class StudentCertificationComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<StudentCertificationState, StudentCertificationEvent>(initialState = StudentCertificationState()) {

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

    fun onClickRoadmapCertButton() {
        setState {
            copy(roadmapCertConfirmDialog = true)
        }
    }

    fun onClickRunningCertButton() {
        setState {
            copy(runningCertConfirmDialog = true)
        }
    }

    fun dismissRoadmapCertDialog() {
        setState {
            copy(roadmapCertConfirmDialog = false)
        }
    }

    fun dismissRunningCertDialog() {
        setState {
            copy(runningCertConfirmDialog = false)
        }
    }

    init {
        GLog.d(TAG, "onCreate")
    }
}