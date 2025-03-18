package com.project.giunne.common.presentation.certification.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.certification.GetCertificationHistory
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.presentation.certification.student.intent.TeacherCertificationEvent
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.student.state.TeacherCertificationState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherCertificationComponent"
class TeacherCertificationComponent(
    componentContext: ComponentContext,
    private val getUploadList: GetUploadList = KoinJavaComponent.get(GetUploadList::class.java),
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<TeacherCertificationState, TeacherCertificationEvent>(initialState = TeacherCertificationState()) {

    fun callUploadList(
        roadmapId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getUploadList.invoke(roadmapId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        uploadList = response
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        loading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

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

    fun dismissErrorDialog() {
        setState {
            copy(error = null)
        }
    }

    init {
        GLog.d(TAG, "onCreate")
    }
}