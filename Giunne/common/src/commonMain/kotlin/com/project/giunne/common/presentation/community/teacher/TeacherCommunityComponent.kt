package com.project.giunne.common.presentation.community.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.community.student.intent.TeacherCommunityEvent
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.presentation.community.student.state.TeacherCommunityState
import com.project.giunne.common.util.GLog
import org.koin.core.component.KoinComponent

private const val TAG = "TeacherCommunityComponent"
class TeacherCommunityComponent(
    componentContext: ComponentContext,
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<TeacherCommunityState, TeacherCommunityEvent>(initialState = TeacherCommunityState()) {
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