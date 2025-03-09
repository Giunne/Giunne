package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.util.NetworkResult

interface RoadMapRepository {
    suspend fun getAllRoadMapList(): NetworkResult<List<RoadMapInfo>>
    suspend fun modifyQuestInfo(modifyQuestInfoRequest: ModifyQuestInfoRequest): NetworkResult<QuestInfo>
    suspend fun getTeacherCourse(roadmapId: Long): NetworkResult<CourseResponse>
}