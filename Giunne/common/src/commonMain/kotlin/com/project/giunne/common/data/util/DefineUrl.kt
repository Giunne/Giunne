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
}