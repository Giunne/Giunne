package com.project.giunne.common.data.repository

import com.project.giunne.common.data.remote.request.GradeStudentRequest
import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.service.CertificationService
import com.project.giunne.common.data.util.NetworkResult
import com.project.giunne.common.data.util.handleApi
import com.project.giunne.common.domain.repository.CertificationRepository
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders

private const val TAG = "CertificationRepositoryImpl"
class CertificationRepositoryImpl(
    private val certificationService: CertificationService
): CertificationRepository {
    override suspend fun getCertificationProgress(roadmapId: Long): NetworkResult<List<StudentQuestInfo>> {
        return handleApi(TAG) {
            certificationService.getCertificationProgress(roadmapId = roadmapId)
        }
    }

    override suspend fun getCertificationHistory(roadmapId: Long): NetworkResult<List<StudentQuestInfo>> {
        return handleApi(TAG) {
            certificationService.getCertificationHistory(roadmapId = roadmapId)
        }
    }

    override suspend fun getUploadList(roadmapId: Long): NetworkResult<List<QuestUploadInfo>> {
        return handleApi(TAG) {
            certificationService.getUploadList(roadmapId = roadmapId)
        }
    }

    override suspend fun postUploadFile(
        questId: Long,
        byteArray: ByteArray,
        mimeType: String,
        onProgress: (Long, Long) -> Unit
    ): NetworkResult<String> {
        return handleApi(TAG) {
            val extension = mimeType.substringAfter("/")
            val multipart = MultiPartFormDataContent(
                formData {
                    append("file", byteArray, Headers.build {
                        append(HttpHeaders.ContentType, mimeType)
                        append(HttpHeaders.ContentDisposition,  "filename=Student-$questId-${byteArray.hashCode()}.$extension")
                    })
                })
            certificationService.postUploadFile(questId, multipart) {
                onUpload { bytesSentTotal, contentLength ->
                    onProgress(bytesSentTotal, contentLength)
                }
            }
        }
    }

    override suspend fun postGradeStudent(gradeStudentRequest: GradeStudentRequest): NetworkResult<String> {
        return handleApi(TAG) {
            certificationService.postGradeStudent(gradeStudentRequest = gradeStudentRequest)
        }
    }
}