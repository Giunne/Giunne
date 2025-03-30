package com.project.giunne.common.presentation.mypage.intent

sealed interface MyPageEvent {
    data class SuccessModifyInformation(
        val message: String
    ) : MyPageEvent
}