package com.project.giunne.common.domain.repository

import com.project.giunne.common.data.remote.request.CreateNoticeRequest
import com.project.giunne.common.data.remote.request.ModifyNoticeRequest
import com.project.giunne.common.data.remote.response.NoticeListResponse
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.util.NetworkResult

interface NoticeRepository {
    suspend fun getNoticeList(pageIndex: Int, recreationId: Int): NetworkResult<NoticeListResponse>
    suspend fun getNoticeDetail(noticeId: Int): NetworkResult<NoticeResponse>
    suspend fun postNewNotice(createNoticeRequest: CreateNoticeRequest): NetworkResult<String>
    suspend fun modifyNotice(modifyNoticeRequest: ModifyNoticeRequest): NetworkResult<String>
    suspend fun deleteNotice(noticeId: Int): NetworkResult<String>
    suspend fun readNotice(noticeId: Int): NetworkResult<String>
}