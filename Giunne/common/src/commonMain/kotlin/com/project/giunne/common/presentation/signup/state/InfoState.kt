package com.project.giunne.common.presentation.signup.state

data class InfoState(
    val idText: String = "",
    val passText: String = "",
    val passConfText: String = "",
    val codeText: String = "",
    val schoolText: String = "",
    val schoolSearchDialog: Boolean = false,
)
