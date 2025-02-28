package com.project.giunne.common.data.util

import com.project.giunne.BuildKonfig

object DefineUrl {
    const val BASE_URL = BuildKonfig.BASE_URL

    /* Auth */
    const val URL_TEACHER_SIGNUP = "member/auth/signup/teacher"
    const val URL_STUDENT_SIGNUP = "member/auth/signup/student"
    const val URL_LOGIN = "member/auth/login"
    const val URL_LOGOUT = "member/auth/logout"

    /* Common */
    const val URL_SCHOOL_LIST = "member/school"

    /* Recreation */
    const val URL_SEARCH_RECREATION = "member/recreation"
    const val URL_CREATE_RECREATION = "member/recreation"
    const val URL_STUDENT_JOIN_RECREATION = "member/recreation/join"

    /* Avatar */
    const val URL_AVATAR_LOGIN = "member/avatar/login"
    const val URL_AVATAR_CREATE = "member/avatar/create"
    const val URL_USER_AVATAR_LIST = "member/avatar"
}