package com.project.giunne.common.presentation.community.student.intent

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.request.CommentLikeRequest
import com.project.giunne.common.data.remote.request.CommentRequest
import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.certification.GetUploadList
import com.project.giunne.common.domain.usecase.certification.PostGradeStudentUseCase
import com.project.giunne.common.domain.usecase.community.DeleteComment
import com.project.giunne.common.domain.usecase.community.GetCommentList
import com.project.giunne.common.domain.usecase.community.GetPostingDetail
import com.project.giunne.common.domain.usecase.community.GetPostingDetailList
import com.project.giunne.common.domain.usecase.community.PostComment
import com.project.giunne.common.domain.usecase.community.PostCommentLike
import com.project.giunne.common.domain.usecase.community.PostCommentUnlike
import com.project.giunne.common.presentation.community.student.state.CommunityState
import com.project.giunne.common.util.Define.playerId
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class CommunityStore(
    private val getPostingDetail: GetPostingDetail = KoinJavaComponent.get(GetPostingDetail::class.java),
    private val postComment: PostComment = KoinJavaComponent.get(PostComment::class.java),
    private val getCommentList: GetCommentList = KoinJavaComponent.get(GetCommentList::class.java),
    private val deleteComment: DeleteComment = KoinJavaComponent.get(DeleteComment::class.java),
    private val postCommentLike: PostCommentLike = KoinJavaComponent.get(PostCommentLike::class.java),
    private val postCommentUnlike: PostCommentUnlike = KoinJavaComponent.get(PostCommentUnlike::class.java),
): BaseStore<CommunityState>(CommunityState()) {
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

    fun callPostComment(
        commentRequest: CommentRequest,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                postComment.invoke(commentRequest)
            }.onSuccess { response ->
                // TODO Success
                setState {
                    copy(loading = false)
                }
                onSuccess()
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

    fun callCommentList(
        postId: Long,
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                getCommentList.invoke(postId, 1)
            }.onSuccess { response ->
                // TODO Success
                setState {
                    copy(
                        loading = false,
                        commentList = response.data,
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

    fun loadNextPage(postId: Long, pageIndex: Int) {
        scope.launch {
            runCatching {
                getCommentList(postId, pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        commentList = (commentList + response.data).distinctBy { it.id },
                        paginationInfo = response.paginationInfo
                    )
                }
            }
        }
    }

    fun callDeleteComment(
        postId: Long,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                deleteComment.invoke(postId)
            }.onSuccess {
                setState { copy(loading = false) }
                onSuccess()
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

    fun callCommentLike(
        commentLikeRequest: CommentLikeRequest,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                postCommentLike.invoke(commentLikeRequest)
            }.onSuccess {
                setState { copy(loading = false) }
                onSuccess()
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

    fun callCommentUnlike(
        commentLikeRequest: CommentLikeRequest,
        onSuccess: () -> Unit
    ) {
        scope.launch {
            setState { copy(loading = true) }
            runCatching {
                postCommentUnlike.invoke(commentLikeRequest)
            }.onSuccess {
                setState { copy(loading = false) }
                onSuccess()
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