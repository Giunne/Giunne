package com.project.giunne.common.util

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.domain.usecase.avatar.GetMyPointUseCase
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

object AvatarUtil: BaseStore<AvatarUserResponse>(AvatarUserResponse()) {
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(GetUserAvatarListUseCase::class.java)
    private val getMyPointUseCase: GetMyPointUseCase = KoinJavaComponent.get(GetMyPointUseCase::class.java)

    private val _levelUpEffects = Channel<Boolean>()
    val levelUpEffects: Flow<Boolean> = _levelUpEffects.receiveAsFlow()

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
                val userResponse = response.data.find { it.id.toLong() == playerId } ?: AvatarUserResponse()

                setUserInfo(avatarUserResponse = userResponse)
                if (Define.currentLevel < userResponse.level) {
                    if (Define.currentLevel > 0) {
                        _levelUpEffects.send(true)
                    }
                    Define.currentLevel = userResponse.level
                }
            }.onFailure {

            }
        }
    }

    fun getMyPointInfo() {
        scope.launch {
            runCatching {
                getMyPointUseCase.invoke()
            }.onSuccess {
                setState {
                    copy(
                        myPoint = it.point
                    )
                }
            }.onFailure {

            }
        }
    }

    fun dismissLevelUpDialog() {
        scope.launch {
            _levelUpEffects.send(false)
        }
    }
}