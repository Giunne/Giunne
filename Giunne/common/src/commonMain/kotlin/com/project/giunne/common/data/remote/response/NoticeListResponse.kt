package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
    @SerialName("isRead")
    val isRead: Boolean = false,
) {
    val isUpdated: Boolean
        get() = createTime != updateTime

    companion object {
        fun String.formatTimeAgo(): String {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val pastTime = LocalDateTime.parse(this, formatter)

            val currentTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))

            // 두 시간의 차이 계산
            val duration = Duration.between(pastTime, currentTime)
            val minutes = duration.toMinutes()
            val hours = duration.toHours()
            val days = duration.toDays()
            val months = days / 30
            val years = days / 365

            // 시간 차이에 따른 문자열 반환
            return when {
                minutes < 1 -> "방금 전"
                minutes < 60 -> "${minutes}분 전"
                hours < 24 -> "${hours}시간 전"
                days < 30 -> "${days}일 전"
                months < 12 -> "${months}달 전"
                else -> "${years}년 전"
            }
        }
    }
}
