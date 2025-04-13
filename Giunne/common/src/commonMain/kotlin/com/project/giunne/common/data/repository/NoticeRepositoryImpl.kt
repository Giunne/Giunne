package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.CreateNoticeRequest
import com.project.giunne.common.data.remote.request.ModifyNoticeRequest
import com.project.giunne.common.data.remote.response.NoticeListResponse
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.UnreadNoticeCountResponse
import com.project.giunne.common.data.service.NoticeService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.NoticeRepository

private const val TAG = "NoticeRepositoryImpl"
class NoticeRepositoryImpl(
    private val noticeService: NoticeService
): NoticeRepository {
    override suspend fun getNoticeList(
        pageIndex: Int,
        recreationId: Int
    ): NetworkResult<NoticeListResponse> {
        return handleApi(TAG) {
            noticeService.getNoticeList(pageIndex, recreationId)
        }
    }

    override suspend fun getNoticeDetail(noticeId: Int): NetworkResult<NoticeResponse> {
        return handleApi(TAG) {
            noticeService.getNoticeDetail(noticeId)
        }
    }

    override suspend fun postNewNotice(createNoticeRequest: CreateNoticeRequest): NetworkResult<String> {
        return handleApi(TAG) {
            noticeService.postNewNotice(createNoticeRequest)
        }
    }

    override suspend fun modifyNotice(modifyNoticeRequest: ModifyNoticeRequest): NetworkResult<String> {
        return handleApi(TAG) {
            noticeService.modifyNotice(modifyNoticeRequest)
        }
    }

    override suspend fun deleteNotice(noticeId: Int): NetworkResult<String> {
        return handleApi(TAG) {
            noticeService.deleteNotice(noticeId)
        }
    }

    override suspend fun readNotice(noticeId: Int): NetworkResult<String> {
        return handleApi(TAG) {
            noticeService.readNotice(noticeId)
        }
    }

    override suspend fun unreadNoticeCount(): NetworkResult<UnreadNoticeCountResponse> {
        return handleApi(TAG) {
            noticeService.unreadNoticeCount()
        }
    }
}