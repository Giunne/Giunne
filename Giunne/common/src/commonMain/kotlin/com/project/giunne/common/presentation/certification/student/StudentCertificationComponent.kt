package com.project.giunne.common.presentation.certification.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.certification.GetCertificationHistory
import com.project.giunne.common.domain.usecase.certification.GetCertificationProgress
import com.project.giunne.common.domain.usecase.certification.PostUploadFileUseCase
import com.project.giunne.common.presentation.certification.student.intent.StudentCertificationEvent
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.student.state.StudentCertificationState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent
import kotlin.coroutines.cancellation.CancellationException

private const val TAG = "StudentCertificationComponent"
class StudentCertificationComponent(
    componentContext: ComponentContext,
    private val getCertificationProgress: GetCertificationProgress = KoinJavaComponent.get(GetCertificationProgress::class.java),
    private val getCertificationHistory: GetCertificationHistory = KoinJavaComponent.get(GetCertificationHistory::class.java),
    private val postUploadFileUseCase: PostUploadFileUseCase = KoinJavaComponent.get(PostUploadFileUseCase::class.java)
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<StudentCertificationState, StudentCertificationEvent>(initialState = StudentCertificationState()) {


    private var uploadJob: Job? = null

    fun callCertificationProgressList(
        roadmapId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getCertificationProgress.invoke(roadmapId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        roadmapProgressList = response,
                        runningProgressList = response
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

    fun callCertificationHistoryList(
        roadmapId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getCertificationHistory.invoke(roadmapId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        roadmapHistoryList = response,
                        runningHistoryList = response
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

    fun uploadFile(
        questId: Long,
        byteArray: ByteArray,
        mimeType: String,
        onProgress: (Long, Long) -> Unit
    ) {
        uploadJob = scope.launch {
            runCatching {
                setState { copy(uploadDialog = true) }
                postUploadFileUseCase(questId, byteArray, mimeType, onProgress)
            }.onSuccess {
                setState {
                    copy(
                        uploadDialog = false,
                        successUpload = true
                    )
                }
            }.onFailure {
                if (it is CancellationException) {
                    setState { copy(loading = false) }
                } else {
                    setState {
                        copy(
                            loading = false,
                            error = it.asDataThrowable()
                        )
                    }
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

    fun dismissErrorDialog() {
        setState {
            copy(error = null)
        }
    }

    fun updateProgress(progress: Float) {
        setState { copy(progress = progress) }
    }

    fun dismissUploadDialog() {
        uploadJob?.cancel()
        setState {
            copy(
                uploadDialog = false
            )
        }
    }

    fun dismissSuccessUploadDialog() {
        setState { copy(successUpload = false) }
    }

    init {
        GLog.d(TAG, "onCreate")
    }
}