package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.ModifyQuestInfoRequest
import com.project.giunne.common.data.remote.request.QuestStateRequest
import com.project.giunne.common.data.remote.response.CourseResponse
import com.project.giunne.common.data.remote.response.QuestCodeResponse
import com.project.giunne.common.data.remote.response.QuestInfo
import com.project.giunne.common.data.remote.response.RoadMapInfo
import com.project.giunne.common.data.remote.response.StudentCourseResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.URL_MODIFY_QUEST
import com.project.giunne.common.data.util.DefineUrl.URL_QUEST_CODE
import com.project.giunne.common.data.util.DefineUrl.URL_QUEST_STATE
import com.project.giunne.common.data.util.DefineUrl.URL_ROADMAP_LIST
import com.project.giunne.common.data.util.DefineUrl.URL_STUDENT_ROADMAP_COURSE
import com.project.giunne.common.data.util.DefineUrl.URL_TEACHER_ROADMAP_COURSE
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

    @GET(URL_TEACHER_ROADMAP_COURSE)
    suspend fun getTeacherCourse(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<CourseResponse>

    @GET(URL_STUDENT_ROADMAP_COURSE)
    suspend fun getStudentCourse(
        @Query("roadmapId") roadmapId: Long
    ): BaseResponse<StudentCourseResponse>

    @GET(URL_QUEST_CODE)
    suspend fun getQuestCode(): BaseResponse<QuestCodeResponse>

    @PUT(URL_QUEST_STATE)
    suspend fun modifyQuestState(
        @Body questStateRequest: QuestStateRequest
    ): BaseResponse<String>
}