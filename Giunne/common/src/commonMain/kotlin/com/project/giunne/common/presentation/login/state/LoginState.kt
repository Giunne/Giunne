package com.project.giunne.common.presentation.login.state

import com.project.giunne.common.data.util.DataThrowable

data class LoginState(
    val idText: String = "",
    val passText: String = "",

    val passwordChangeSuccessDialog: Boolean = false,
    val passwordSetupDialog: Boolean = false,

    val loading: Boolean = false,
    val error: DataThrowable? = null
)