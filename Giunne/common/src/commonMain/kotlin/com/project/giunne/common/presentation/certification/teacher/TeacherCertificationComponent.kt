package com.project.giunne.common.presentation.certification.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.certification.GetCertificationHistory
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.community.GetPostingDetailList
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
    initialPage: CertPage,
    private val getUploadList: GetUploadList = KoinJavaComponent.get(GetUploadList::class.java),
    private val getPostingDetailList: GetPostingDetailList = KoinJavaComponent.get(GetPostingDetailList::class.java),
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<TeacherCertificationState, TeacherCertificationEvent>(initialState = TeacherCertificationState(pageType = initialPage)) {

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

    fun callPostingDetailList(
        playerId: Long,
        questId: Long,
        onSuccess: (Long, String) -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getPostingDetailList.invoke(playerId, questId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        postingDetailListInfo = response
                    )
                }
                onSuccess(
                    response.postInfoList.last().id.toLong(),
                    response.questInfo.getQuestTitle()
                )
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