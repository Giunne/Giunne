package com.project.giunne.common.data.util

import com.project.giunne.BuildKonfig

object DefineUrl {
    const val BASE_URL = BuildKonfig.BASE_URL
    const val IMAGE_BASE_URL = BuildKonfig.IMAGE_BASE_URL

    // Shop
    const val GET_CATEGORY_ITEM_BY_ID = "items/category"
    const val GET_CATEGORY_ROOT = "items/category/root"
    const val GET_GACHA_TYPE = "items/order/gacha-type"

    /* Auth */
    const val URL_TEACHER_SIGNUP = "member/auth/signup/teacher"
    const val URL_STUDENT_SIGNUP = "member/auth/signup/student"
    const val URL_LOGIN = "member/auth/login"
    const val URL_LOGOUT = "member/auth/logout"

    /* Common */
    const val URL_SCHOOL_LIST = "member/school"
}