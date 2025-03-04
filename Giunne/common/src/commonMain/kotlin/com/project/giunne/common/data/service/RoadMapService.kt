package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_MODIFY_QUEST
import com.project.giunne.common.data.util.DefineUrl.URL_ROADMAP_COURSE
import com.project.giunne.common.data.util.DefineUrl.URL_ROADMAP_LIST
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Query

interface RoadMapService {
    @GET(URL_ROADMAP_LIST)
    suspend fun getAllRoadMapList(
    ): BaseResponse<List<RoadMapInfo>>

    @PUT(URL_MODIFY_QUEST)
    suspend fun modifyQuestInfo(
        @Body modifyQuestInfoRequest: ModifyQuestInfoRequest
    ): BaseResponse<QuestInfo>

    @GET(URL_ROADMAP_COURSE)
    suspend fun getCourse(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<CourseResponse>
}