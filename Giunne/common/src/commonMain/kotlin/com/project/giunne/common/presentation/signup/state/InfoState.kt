package com.project.giunne.common.presentation.signup.state

import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.SchoolInfo
import com.project.giunne.common.data.util.DataThrowable

data class InfoState(
    val idText: String = "",
    val passText: String = "",
    val passConfText: String = "",
    val codeText: String = "",
    val schoolInfo: SchoolInfo = SchoolInfo(),
    val schoolSearchDialog: Boolean = false,
    val signupValidate: String? = null,

    val pageNationInfo: PaginationInfo = PaginationInfo(),

    val schoolList: List<SchoolInfo> = listOf(),
    val error: DataThrowable? = null,
    val loading: Boolean = false,

    val signupSuccessDialog: Boolean = false
)
