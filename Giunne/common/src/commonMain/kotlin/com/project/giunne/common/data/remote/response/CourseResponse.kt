package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    @SerialName("courseInfo") val courseInfo: Map<Long, List<CourseInfo>> = mapOf(),
)