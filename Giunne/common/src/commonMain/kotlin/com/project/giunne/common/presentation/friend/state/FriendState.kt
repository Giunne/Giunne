package com.project.giunne.common.presentation.friend.state

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.data.util.DataThrowable
import com.project.giunne.common.presentation.certification.student.state.CertPage

data class FriendState(
    val pageType: CertPage = CertPage.RoadMap,
    val roadmapId: Long = 1,
    val friendsList: List<AvatarUserResponse> = listOf(),
    val loading: Boolean = false,
    val showModifyCheckDialog: Boolean = false,
    val showModifySuccessDialog: Boolean = false,
    val showStudentCourse: Boolean = false,
    val courseMap: Map<Long, List<StudentCourseInfo>> = mapOf(),
    val error: DataThrowable? = null
)