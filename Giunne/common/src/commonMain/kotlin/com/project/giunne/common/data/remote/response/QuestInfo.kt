package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("courseName") val courseName: String = "",
    @SerialName("title") val title: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("color") val color: ColorInfo? = ColorInfo(),
    @SerialName("difficultyLevel") val difficultyLevel: Int = 0,
    @SerialName("isTeam") val isTeam: Boolean = false,
    @SerialName("cooperationType") val cooperationType: String = "",
    @SerialName("trainingType") val trainingType: String = "",
    @SerialName("deadline") val deadline: String = "",
    @SerialName("sortSeq") val sortSeq: Int = 0,
    @SerialName("roadMapId") val roadMapId: Int = 0,
    @SerialName("position") val position: Position = Position(),
    @SerialName("isRoot") val isRoot: Boolean = false,
    @SerialName("isLeaf") val isLeaf: Boolean = false,
    @SerialName("thumbnailUrl") val thumbnailUrl: String? = "",
    @SerialName("currentApproveCnt") val currentApproveCnt: Int = 0,
    @SerialName("needApproveCnt") val needApproveCnt: Int = 0,
    @SerialName("rewardPoint") val rewardPoint: Long = 0,
    @SerialName("rewardExp") val rewardExp: Long = 0,
    @SerialName("trainingDescription") val trainingDescription: String = "",
    @SerialName("guideUrl") val guideUrl: String = "",
    @SerialName("parent") val parent: List<Long> = listOf(),
)

@Serializable
data class ColorInfo(
    @SerialName("blue")
    val blue: Int = 0,
    @SerialName("green")
    val green: Int = 0,
    @SerialName("red")
    val red: Int = 0
)

@Serializable
data class Position(
    @SerialName("positionX") val positionX: Double = 0.0,
    @SerialName("positionY") val positionY: Double = 0.0,
)