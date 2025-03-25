package com.project.giunne.common.presentation.community.teacher

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.community.GetPostingDetail
import com.project.giunne.common.domain.usecase.community.GetPostingList
import com.project.giunne.common.domain.usecase.community.GetQuestTypeList
import com.project.giunne.common.presentation.community.student.intent.TeacherCommunityEvent
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.presentation.community.student.state.TeacherCommunityState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "TeacherCommunityComponent"
class TeacherCommunityComponent(
    componentContext: ComponentContext,
    private val getPostingList: GetPostingList = KoinJavaComponent.get(GetPostingList::class.java),
    private val getQuestTypeList: GetQuestTypeList = KoinJavaComponent.get(GetQuestTypeList::class.java),
): KoinComponent, ComponentContext by componentContext,
    BaseComponent<TeacherCommunityState, TeacherCommunityEvent>(initialState = TeacherCommunityState()) {
    init {
        GLog.d(TAG, "onCreate")
    }

    fun callPostingList(
        roadMapId: Long,
        questName: String,
        nickName: String,
        pageIndex: Int,
        sortDirection: String
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getPostingList.invoke(
                    roadMapId = roadMapId,
                    questName = questName,
                    nickName = nickName,
                    pageIndex = pageIndex,
                    sortDirection = sortDirection,
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        postingList = if (pageIndex == 1) response.data
                            else (postingList + response.data).distinctBy { it.id },
                        paginationInfo = response.paginationInfo
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

    fun callQuestTypeList(
        roadmapId: Long,
        pageIndex: Int = 1
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getQuestTypeList.invoke(
                    roadmapId = roadmapId,
                    pageIndex = pageIndex,
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        questTypeList = response.data
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

    fun onClickSearchButton(
        roadMapId: Long,
        questName: String,
        nickName: String,
        sortDirection: String
    ) {
        callPostingList(
            roadMapId = roadMapId,
            questName = questName,
            nickName = nickName,
            pageIndex = 1,
            sortDirection = sortDirection,
        )
    }

    fun onSearchTextChanged(
        text: String
    ) {
        setState { copy(searchText = text) }
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

    fun dismissErrorDialog() {
        setState {
            copy(error = null)
        }
    }
}