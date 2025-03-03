package com.project.giunne.common.presentation.signup.state

import com.project.giunne.common.data.remote.response.PageNationInfo
import com.project.giunne.common.data.remote.response.SchoolInfo
import com.project.giunne.common.data.util.DataThrowable

data class InfoState(
    val idText: String = "",
    val nameText: String = "",
    val passText: String = "",
    val passConfText: String = "",
    val codeText: String = "",
    val schoolInfo: SchoolInfo = SchoolInfo(),
    val schoolSearchDialog: Boolean = false,
    val signupValidate: String? = null,

    val pageNationInfo: PageNationInfo = PageNationInfo(),

    val schoolList: List<SchoolInfo> = listOf(),
    val error: DataThrowable? = null,
    val loading: Boolean = false,

    val signupSuccessDialog: Boolean = false
)
