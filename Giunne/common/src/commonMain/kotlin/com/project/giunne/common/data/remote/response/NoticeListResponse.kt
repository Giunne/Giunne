package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoticeListResponse(
    @SerialName("data")
    val list: List<NoticeResponse> = listOf(),
    @SerialName("paginationInfo")
    val paginationInfo: PaginationInfo = PaginationInfo()
)
@Serializable
data class NoticeResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("writerId")
    val writerId: Int? = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("content")
    val content: String = "",
    @SerialName("createTime")
    val createTime: String = "",
    @SerialName("updateTime")
    val updateTime: String = "",
)
