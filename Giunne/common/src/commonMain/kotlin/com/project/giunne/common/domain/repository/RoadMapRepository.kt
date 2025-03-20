package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.request.QuestStateRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestCodeResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.remote.response.StudentCourseResponse
import com.project.giunne.common.data.util.NetworkResult

interface RoadMapRepository {
    suspend fun getAllRoadMapList(): NetworkResult<List<RoadMapInfo>>
    suspend fun modifyQuestInfo(modifyQuestInfoRequest: ModifyQuestInfoRequest): NetworkResult<QuestInfo>
    suspend fun getTeacherCourse(roadmapId: Long): NetworkResult<CourseResponse>
    suspend fun getStudentCourse(roadmapId: Long): NetworkResult<StudentCourseResponse>
    suspend fun getQuestCode(): NetworkResult<QuestCodeResponse>
    suspend fun modifyQuestState(questStateRequest: QuestStateRequest): NetworkResult<String>
}