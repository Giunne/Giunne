package com.project.giunne.common.presentation.community.student

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.community.student.intent.StudentCommunityEvent
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.presentation.community.student.state.StudentCommunityState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

private const val TAG = "StudentCommunityCompone"
class StudentCommunityComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<StudentCommunityState, StudentCommunityEvent>(initialState = StudentCommunityState()) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun onClickSearchButton(
        text: String
    ) {

    }

    fun onSelectDatePriority(
        datePriority: DatePriority
    ) {
        setState {
            copy(datePriority = datePriority, datePrioritySelectDialog = false)
        }
    }

    fun onSelectRoadmapFilter(
        roadmapFilter: String
    ) {
        setState {
            copy(roadmapFilter = roadmapFilter, roadmapFilterDialog = false)
        }
    }

    fun onSelectRunningFilter(
        roadmapFilter: String
    ) {
        setState {
            copy(runningFilter = roadmapFilter, runningFilterDialog = false)
        }
    }

    fun onClickDatePriorityButton() {
        setState {
            copy(datePrioritySelectDialog = true)
        }
    }

    fun onClickRoadmapFilterButton() {
        setState {
            copy(roadmapFilterDialog = true)
        }
    }

    fun onClickRunningFilterButton() {
        setState {
            copy(runningFilterDialog = true)
        }
    }

    fun dismissDatePriorityDialog() {
        setState {
            copy(datePrioritySelectDialog = false)
        }
    }

    fun dismissRoadmapFilterDialog() {
        setState {
            copy(roadmapFilterDialog = false)
        }
    }

    fun dismissRunningFilterDialog() {
        setState {
            copy(runningFilterDialog = false)
        }
    }

}