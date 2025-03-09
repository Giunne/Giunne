package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("courseName") val courseName: String = "",
    @SerialName("title") val title: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("color") val color: ColorInfo? = ColorInfo(),
    @SerialName("sortSeq") val sortSeq: Int = 0,
    @SerialName("roadMapId") val roadMapId: Int = 0,
    @SerialName("position") val position: Position = Position(),
    @SerialName("isRoot") val isRoot: Boolean = false,
    @SerialName("isLeaf") val isLeaf: Boolean = false,
    @SerialName("thumbnailUrl") val thumbnailUrl: String? = "",
    @SerialName("parent") val parent: List<Long> = listOf(),
    @SerialName("questInfo") val questInfo: QuestInfo = QuestInfo()
)

@Serializable
data class QuestInfo(
    @SerialName("cooperationType")
    val cooperationType: String = "",
    @SerialName("currentApproveCount")
    val currentApproveCount: Int = 0,
    @SerialName("deadline")
    val deadline: String = "",
    @SerialName("difficultyLevel")
    val difficultyLevel: Int = 0,
    @SerialName("guideUrl")
    val guideUrl: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("isTeam")
    val isTeam: Boolean = false,
    @SerialName("maxPlayer")
    val maxPlayer: Int = 0,
    @SerialName("minPlayer")
    val minPlayer: Int = 0,
    @SerialName("needApproveCount")
    val needApproveCount: Int = 0,
    @SerialName("needLevel")
    val needLevel: Int = 0,
    @SerialName("questDescription")
    val questDescription: String = "",
    @SerialName("questName")
    val questName: String = "",
    @SerialName("questStateInfos")
    val questStateInfos: List<QuestStateInfo> = listOf(),
    @SerialName("questType")
    val questType: String = "",
    @SerialName("rewardExp")
    val rewardExp: Int = 0,
    @SerialName("rewardPoint")
    val rewardPoint: Int = 0,
    @SerialName("sortSeq")
    val sortSeq: Int = 0,
    @SerialName("trainingDescription")
    val trainingDescription: String = "",
    @SerialName("trainingType")
    val trainingType: String = ""
)

@Serializable
data class QuestStateInfo(
    @SerialName("hasExtraPoints")
    val hasExtraPoints: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("playerId")
    val playerId: Int = 0,
    @SerialName("questProgress")
    val questProgress: String = "",
    @SerialName("rewardExp")
    val rewardExp: Int = 0,
    @SerialName("rewardPoint")
    val rewardPoint: Int = 0,
    @SerialName("starPoint")
    val starPoint: Int = 0
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