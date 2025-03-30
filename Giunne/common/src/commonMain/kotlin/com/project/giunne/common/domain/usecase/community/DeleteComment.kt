package com.project.giunne.common.domain.usecase.community

import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CommunityRepository

class DeleteComment(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(postId: Long): String {
        return communityRepository
            .deleteComment(postId = postId)
            .successOr("")
    }
}