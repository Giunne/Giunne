package com.project.giunne.common.presentation.community.student.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.domain.usecase.auth.StudentSignupUseCase
import com.project.giunne.common.presentation.community.student.state.GradeState
import org.koin.java.KoinJavaComponent

class GradeStore(
): BaseStore<GradeState>(GradeState()) {
    fun onClickGradeButton() {
        setState { copy(gradeDialog = true) }
    }

    fun onClickConfirmButton() {
        setState { copy(confirmDialog = true) }
    }

    fun dismissGradeDialog() {
        setState { copy(gradeDialog = false) }
    }

    fun dismissConfirmDialog() {
        setState { copy(confirmDialog = false) }
    }
}