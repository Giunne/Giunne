package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageNationRequest(
    @SerialName("pageIndex")
    val pageIndex: Int = 0,
    @SerialName("pageSize")
    val pageSize: Int = 0,
    @SerialName("sortProperty")
    val sortProperty: String = "",
    @SerialName("sortDirection")
    val sortDirection: String = "",
)