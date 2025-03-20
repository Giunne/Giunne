package com.project.giunne.common.presentation.notification

import com.project.giunne.common.base.BaseStore
import com.project.giunne.common.presentation.notification.state.NotificationState

object NotificationUtil: BaseStore<NotificationState>(NotificationState()) {
    fun onClickNotificationButton() {
        setState { copy(isOpen = true) }
    }

    fun closeNotificationScreen() {
        setState { copy(isOpen = false) }
    }
}