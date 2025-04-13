package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.request.QuestStateRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestCodeResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.remote.response.StudentCourseResponse
import com.project.giunne.common.data.service.RoadMapService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.TokenHandler.handleTokenForResponse
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.RoadMapRepository

private const val TAG = "RoadMapRepositoryImpl"
class RoadMapRepositoryImpl(
    private val roadMapService: RoadMapService
): RoadMapRepository {
    override suspend fun getAllRoadMapList(): NetworkResult<List<RoadMapInfo>> {
        val response = handleTokenForResponse {
            handleTokenForResponse {
                roadMapService.getAllRoadMapList()
            }
        }

        return handleApi(TAG) { response }
    }

    override suspend fun modifyQuestInfo(modifyQuestInfoRequest: ModifyQuestInfoRequest): NetworkResult<QuestInfo> {
        val response = handleTokenForResponse { roadMapService.modifyQuestInfo(modifyQuestInfoRequest = modifyQuestInfoRequest) }

        return handleApi(TAG) { response }
    }

    override suspend fun getTeacherCourse(roadmapId: Long): NetworkResult<CourseResponse> {
        val response = handleTokenForResponse { roadMapService.getTeacherCourse(roadmapId = roadmapId) }

        return handleApi(TAG) { response }
    }

    override suspend fun getSpecificStudentCourse(
        roadmapId: Long,
        playerId: Int
    ): NetworkResult<StudentCourseResponse> {
        val response = handleTokenForResponse {
            roadMapService.getSpecificStudentCourse(
                roadmapId = roadmapId,
                playerId = playerId
            )
        }

        return handleApi(TAG) { response }
    }

    override suspend fun getStudentCourse(roadmapId: Long): NetworkResult<StudentCourseResponse> {
        val response = handleTokenForResponse { roadMapService.getStudentCourse(roadmapId = roadmapId) }

        return handleApi(TAG) { response }
    }

    override suspend fun getQuestCode(): NetworkResult<QuestCodeResponse> {
        val response = handleTokenForResponse { roadMapService.getQuestCode() }

        return handleApi(TAG) { response }
    }

    override suspend fun modifyQuestState(questStateRequest: QuestStateRequest): NetworkResult<String> {
        val response = handleTokenForResponse { roadMapService.modifyQuestState(questStateRequest) }

        return handleApi(TAG) { response }
    }
}