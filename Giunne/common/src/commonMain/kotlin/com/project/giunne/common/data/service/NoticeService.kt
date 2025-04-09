package com.project.giunne.common.data.service

import com.project.giunne.common.data.remote.request.CreateNoticeRequest
import com.project.giunne.common.data.remote.request.ModifyNoticeRequest
import com.project.giunne.common.data.remote.response.NoticeListResponse
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.util.BaseResponse
import com.project.giunne.common.data.util.DefineUrl.DELETE_NOTICE
import com.project.giunne.common.data.util.DefineUrl.GET_NOTICE_DETAIL
import com.project.giunne.common.data.util.DefineUrl.GET_NOTICE_LIST
import com.project.giunne.common.data.util.DefineUrl.MODIFY_NOTICE
import com.project.giunne.common.data.util.DefineUrl.POST_NOTICE
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface NoticeService {
    @GET(GET_NOTICE_LIST)
    suspend fun getNoticeList(
        @Query("pageIndex") pageIndex: Int,
        @Query("recreationId") recreationId: Int
    ): BaseResponse<NoticeListResponse>

    @GET(GET_NOTICE_DETAIL)
        suspend fun getNoticeDetail(
        @Path("id") noticeId: Int
    ): BaseResponse<NoticeResponse>

    @POST(POST_NOTICE)
    suspend fun postNewNotice(
        @Body createNoticeRequest: CreateNoticeRequest
    ): BaseResponse<String>

    @PUT(MODIFY_NOTICE)
    suspend fun modifyNotice(
        @Body modifyNoticeRequest: ModifyNoticeRequest
    ): BaseResponse<String>

    @DELETE(DELETE_NOTICE)
    suspend fun deleteNotice(
        @Path("id") noticeId: Int
    ): BaseResponse<String>

}
