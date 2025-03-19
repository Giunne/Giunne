package com.project.giunne.common.presentation.community.student.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.community.GetPostingDetail
import com.project.giunne.common.domain.usecase.community.GetPostingDetailList
import com.project.giunne.common.presentation.community.student.state.CommunityState
import com.project.giunne.common.util.Define.playerId
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class CommunityStore(
    private val getPostingDetailList: GetPostingDetailList = KoinJavaComponent.get(GetPostingDetailList::class.java),
    private val getPostingDetail: GetPostingDetail = KoinJavaComponent.get(GetPostingDetail::class.java),
): BaseStore<CommunityState>(CommunityState()) {
    fun callPostingDetailList(
        playerId: Long,
        questId: Long
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

    fun callPostingDetail(
        postId: Long
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getPostingDetail.invoke(postId)
            }.onSuccess { response ->
                setState {
                    copy(
                        loading = false,
                        postingDetailInfo = response
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

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }
}