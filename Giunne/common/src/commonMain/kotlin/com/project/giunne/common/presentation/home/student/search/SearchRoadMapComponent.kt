package com.project.giunne.common.presentation.home.student.search

import com.arkivanov.decompose.ComponentContext
import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.roadmap.GetSearchRecreationUseCase
import com.project.giunne.common.presentation.home.student.state.SearchRoadmapEvent
import com.project.giunne.common.presentation.home.student.state.SearchRoadmapState
import com.project.giunne.common.util.GLog
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent

private const val TAG = "SearchRoadMapComponent"
class SearchRoadMapComponent(
    componentContext: ComponentContext,
    private val getSearchRecreationUseCase: GetSearchRecreationUseCase = KoinJavaComponent.get(GetSearchRecreationUseCase::class.java)
): KoinComponent, ComponentContext by componentContext, BaseComponent<SearchRoadmapState, SearchRoadmapEvent>(
    initialState = SearchRoadmapState()
) {

    init {
        GLog.d(TAG, "onCreate")
    }

    fun searchRecreation(
        searchQuery: String,
        pageIndex: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getSearchRecreationUseCase(
                    searchQuery = searchQuery,
                    pageIndex = pageIndex
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        searchRecreationList = response.data
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun loadMore(
        searchQuery: String,
        pageIndex: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getSearchRecreationUseCase(
                    searchQuery = searchQuery,
                    pageIndex = pageIndex
                )
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        searchRecreationList = (searchRecreationList + response.data).distinctBy { it.id }
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }
}