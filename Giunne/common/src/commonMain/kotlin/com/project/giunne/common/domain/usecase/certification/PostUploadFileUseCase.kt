package com.project.giunne.common.domain.usecase.certification

import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.CertificationRepository
import java.io.File

class PostUploadFileUseCase(
    private val certificationRepository: CertificationRepository
) {
    suspend operator fun invoke(questId: Long, file: File): String {
        return certificationRepository
            .postUploadFile(questId, file)
            .successOr("")
    }
}