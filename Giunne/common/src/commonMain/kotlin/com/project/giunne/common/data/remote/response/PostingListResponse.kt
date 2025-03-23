package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostingListResponse(
    @SerialName("data") val data: List<StudentPostingInfo> = listOf(),
    @SerialName("paginationInfo") val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class StudentPostingInfo(
    @SerialName("id") val id : Long = 0,
    @SerialName("questName") val questName : String = "",
    @SerialName("questType") val questType : String = "",
    @SerialName("cooperationType") val cooperationType : String = "",
    @SerialName("trainingType") val trainingType : String = "",
    @SerialName("needApproveCount") val needApproveCount : Int = 0,
    @SerialName("currentApproveCount") val currentApproveCount : Int? = 0,
    @SerialName("questProgress") val questProgress : String = "",
    @SerialName("questPostId") val questPostId : Long = 0,
    @SerialName("questPostProgressType") val questPostProgressType : String = "",
    @SerialName("createTime") val createTime : String = "",
    @SerialName("updateTime") val updateTime : String = "",
    @SerialName("commentCount") val commentCount : Long = 0,
    @SerialName("nickname") val nickname : String = "",
    @SerialName("playerInfo") val playerInfo : AvatarUserResponse = AvatarUserResponse(),
) {
    fun getQuestTitle(): String {
        return trainingType.convertType() + " " + questName.replace(".", "단계 ")
    }
}
