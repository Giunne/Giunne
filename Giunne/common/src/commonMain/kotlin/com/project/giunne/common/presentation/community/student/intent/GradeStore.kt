package com.project.giunne.common.presentation.community.student.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.auth.StudentSignupUseCase
import com.project.giunne.common.domain.usecase.certification.PostGradeStudentUseCase
import com.project.giunne.common.presentation.community.student.state.GradeState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class GradeStore(
    private val postGradeStudent: PostGradeStudentUseCase = KoinJavaComponent.get(PostGradeStudentUseCase::class.java)
): BaseStore<GradeState>(GradeState()) {

    fun gradingStudent(
        gradeStudentRequest: GradeStudentRequest
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                postGradeStudent.invoke(gradeStudentRequest)
            }.onSuccess {
                setState {
                    copy(
                        loading = false,
                        successDialog = true
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

    fun onClickGradeButton() {
        setState { copy(gradeDialog = true) }
    }

    fun onClickConfirmButton(
        star: Int,
        isChecked: Boolean,
        isPass: Boolean
    ) {
        setState {
            copy(
                confirmDialog = true,
                starPoint = star,
                hasExtraPoints = isChecked,
                isPass = isPass
            )
        }
    }

    fun dismissSuccessDialog() {
        setState { copy(successDialog = false) }
    }

    fun dismissGradeDialog() {
        setState { copy(gradeDialog = false) }
    }

    fun dismissConfirmDialog() {
        setState { copy(confirmDialog = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

}