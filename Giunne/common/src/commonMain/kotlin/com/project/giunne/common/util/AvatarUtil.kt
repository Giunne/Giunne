package com.project.giunne.common.util

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

object AvatarUtil: BaseStore<AvatarUserResponse>(AvatarUserResponse()) {
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(GetUserAvatarListUseCase::class.java)


    fun setUserInfo(
        avatarUserResponse: AvatarUserResponse
    ) {
        setState {
            copy(
                characterNo = avatarUserResponse.characterNo,
                exp = avatarUserResponse.exp,
                needExp = avatarUserResponse.needExp,
                id = avatarUserResponse.id,
                level = avatarUserResponse.level,
                nickname = avatarUserResponse.nickname,
                point = avatarUserResponse.point,
                recreationCode = avatarUserResponse.recreationCode,
                recreationId = avatarUserResponse.recreationId,
                recreationName = avatarUserResponse.recreationName,
                teacherId = avatarUserResponse.teacherId,
                teacherLoginId = avatarUserResponse.teacherLoginId,
                teacherName = avatarUserResponse.teacherName,
                wearingItemIds = avatarUserResponse.wearingItemIds,
                wearingItems = avatarUserResponse.wearingItems,
            )
        }
    }

    fun getRecreationList(
        playerId: Long,
        pageIndex: Int
    ) {
        scope.launch {
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                setUserInfo(
                    avatarUserResponse = response.data.find { it.id.toLong() == playerId } ?: AvatarUserResponse()
                )
            }.onFailure {

            }
        }
    }
}