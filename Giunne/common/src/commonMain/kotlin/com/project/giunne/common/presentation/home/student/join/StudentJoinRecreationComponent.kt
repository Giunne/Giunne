package com.project.giunne.common.presentation.home.student.join

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetRecreationStudentJoinUseCase
import com.project.giunne.common.presentation.home.student.state.StudentJoinEvent
import com.project.giunne.common.presentation.home.student.state.StudentJoinState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "StudentJoinRecreationComponent"
class StudentJoinRecreationComponent(
    componentContext: ComponentContext,
    private val getRecreationStudentJoinUseCase: GetRecreationStudentJoinUseCase = KoinJavaComponent.get(GetRecreationStudentJoinUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<StudentJoinState, StudentJoinEvent>(
    initialState = StudentJoinState()
) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun getJoinRecreationList(
        pageIndex: Int = 1
    ) {
        scope.launch {
            runCatching {
                getRecreationStudentJoinUseCase(pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        recreationStudentJoinList = (recreationStudentJoinList + response.data).distinctBy { it.id },
                        paginationInfo = response.paginationInfo
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

}