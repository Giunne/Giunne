package com.project.giunne.common.data.util

import com.project.giunne.BuildKonfig

object DefineUrl {
    const val BASE_URL = BuildKonfig.BASE_URL
    const val IMAGE_BASE_URL = BuildKonfig.IMAGE_BASE_URL

    // Shop
    const val GET_CATEGORY_ITEM_BY_ID = "items/category"
    const val GET_CATEGORY_ROOT = "items/category/root"
    const val GET_GACHA_TYPE = "items/order/gacha-type"
    const val POST_GACHA = "items/order/gacha"

    /* Auth */
    const val URL_TEACHER_SIGNUP = "member/auth/signup/teacher"
    const val URL_STUDENT_SIGNUP = "member/auth/signup/student"
    const val URL_LOGIN = "member/auth/login"
    const val URL_LOGOUT = "member/auth/logout"
    const val URL_REFRESH = "member/auth/access-token/issue"
    const val URL_CHANGE_PASSWORD = "member/auth/password-change"

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
    const val URL_RECREATION_AVATAR_LIST = "member/avatar/recreation-students"

    /* MyPage */
    const val PUT_INVENTORY_ITEM = "member/inventory"
    const val GET_INVENTORY_ITEM_BY_ID = "member/inventory/category"
}