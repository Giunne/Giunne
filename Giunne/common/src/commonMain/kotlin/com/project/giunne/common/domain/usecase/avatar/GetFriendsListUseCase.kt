package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetFriendsListUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(recreationId: Long): List<AvatarUserResponse> {
        return avatarRepository
            .getRecreationAvatarList(recreationId = recreationId)
            .successOr(listOf())
            .filter { !it.nickname.contains("선생님") }
            .sortedWith(
                comparator = compareBy<AvatarUserResponse> { it.exp }.reversed()
            )
            .sortedWith(
                comparator = compareBy<AvatarUserResponse> { it.level }.reversed()
            )
            .mapIndexed { index, avatarUserResponse ->
                avatarUserResponse.copy(ranking = index + 1)
            }
    }
}