package com.project.giunne.common.data.util

import com.project.giunne.BuildKonfig

object DefineUrl {
    const val BASE_URL = BuildKonfig.BASE_URL
    const val IMAGE_BASE_URL = BuildKonfig.IMAGE_BASE_URL

    // Shop
    const val GET_CATEGORY_ITEM_BY_ID = "items/category"
    const val GET_CATEGORY_ROOT = "items/category/root"
    const val GET_GACHA_TYPE = "items/order/gacha-type"
}