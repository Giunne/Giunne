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
    const val GET_POSSIBLE_ITEM_COUNT = "items/order/count-possible-gacha"

    /* Auth */
    const val URL_TEACHER_SIGNUP = "member/auth/signup/teacher"
    const val URL_STUDENT_SIGNUP = "member/auth/signup/student"
    const val URL_LOGIN = "member/auth/login"
    const val URL_LOGOUT = "member/auth/logout"
    const val URL_REFRESH = "member/auth/access-token/issue"
    const val URL_CHANGE_PASSWORD = "member/auth/password-change"
    const val URL_ID_CHECK = "member/auth/duplicate-id"
    const val URL_RESET_PASSWORD = "member/avatar/teacher/password-change"
    const val URL_CHANGE_STUDENT_PASSWORD = "member/auth/student/password-change"

    /* Common */
    const val URL_SCHOOL_LIST = "member/school"

    /* Recreation */
    const val URL_SEARCH_RECREATION = "member/recreation"
    const val URL_CREATE_RECREATION = "member/recreation"
    const val URL_TEACHER_RECREATION_LIST = "member/recreation/my-recreation"

    /* Avatar */
    const val URL_AVATAR_LOGIN = "member/avatar/login"
    const val URL_AVATAR_CREATE = "member/avatar/create"
    const val URL_USER_AVATAR_LIST = "member/avatar"
    const val URL_GET_POINT_INFO = "member/avatar/point"
    const val URL_MODIFY_USER_POINT = "member/avatar/point"
    const val URL_MODIFY_USER_EXP = "member/avatar/increase-experience"

    /* RoadMap */
    const val URL_ROADMAP_LIST = "quest/roadmap"
    const val URL_MODIFY_QUEST = "quest/quest"
    const val URL_TEACHER_ROADMAP_COURSE = "quest/course/teacher/road-map"
    const val URL_SPECIFIC_STUDENT_ROADMAP_COURSE = "quest/course/teacher/road-map-view"
    const val URL_STUDENT_ROADMAP_COURSE = "quest/course/road-map"
    const val URL_QUEST_STATE = "quest/quest-state/quest-progress"
    const val URL_QUEST_CODE = "quest/quest-state/common-code"
    const val URL_RECREATION_AVATAR_LIST = "member/avatar/recreation-students"

    /* MyPage */
    const val PUT_INVENTORY_ITEM = "member/inventory"
    const val GET_INVENTORY_ITEM_BY_ID = "member/inventory/category"
    const val GET_AVATAR_INFORMATION = "member/avatar/school"
    const val MODIFY_AVATAR_INFORMATION = "member/avatar"

    /* Certification */
    const val GET_CERT_PROGRESS = "quest/certification/student/in-progress"
    const val GET_CERT_HISTORY = "quest/certification/student/history"
    const val POST_UPLOAD_FILE = "quest/certification/student"
    const val GET_UPLOAD_LIST = "quest/certification/teacher"
    const val POST_CERT_STUDENT  = "quest/certification/teacher/certificate"

    /* Community */
    const val GET_POSTING_DETAIL_LIST = "quest/post"
    const val GET_POSTING_DETAIL = "quest/post"
    const val POST_COMMENT = "quest/post"
    const val GET_COMMENT_LIST = "quest/post/comment"
    const val DELETE_COMMENT = "quest/post/comment"
    const val POST_COMMENT_LIKE = "quest/post/comment/like"
    const val POST_COMMENT_UNLIKE = "quest/post/comment/unlike"
    const val GET_POSTING_LIST = "quest/quest/upload"
    const val GET_QUEST_TYPE = "quest/quest/quest-type"

    /* Notice */
    const val GET_NOTICE_LIST = "member/notice"
    const val GET_NOTICE_DETAIL = "member/notice/{id}"
    const val MODIFY_NOTICE = "member/notice"
    const val POST_NOTICE = "member/notice"
    const val DELETE_NOTICE = "member/notice{id}"
}