package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.service.RoadMapService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.RoadMapRepository

private const val TAG = "RoadMapRepositoryImpl"
class RoadMapRepositoryImpl(
    private val roadMapService: RoadMapService
): RoadMapRepository {
    override suspend fun getAllRoadMapList(): NetworkResult<List<RoadMapInfo>> {
        return handleApi(TAG) {
            roadMapService.getAllRoadMapList()
        }
    }

    override suspend fun modifyQuestInfo(modifyQuestInfoRequest: ModifyQuestInfoRequest): NetworkResult<QuestInfo> {
        return handleApi(TAG) {
            roadMapService.modifyQuestInfo(modifyQuestInfoRequest = modifyQuestInfoRequest)
        }
    }

    override suspend fun getCourse(roadmapId: Long): NetworkResult<CourseResponse> {
        return handleApi(TAG) {
            roadMapService.getCourse(roadmapId = roadmapId)
        }
    }
}