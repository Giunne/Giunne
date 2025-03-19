package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentCourseResponse(
    @SerialName("courseInfo") val courseInfo: Map<Long, List<StudentCourseInfo>> = mapOf(),
)

@Serializable
data class StudentCourseInfo(
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
    @SerialName("questInfo") val questInfo: StudentQuestInfo = StudentQuestInfo()
)

@Serializable
data class StudentQuestInfo(
    @SerialName("cooperationType") val cooperationType: String = "",
    @SerialName("currentApproveCount") val currentApproveCount: Int = 0,
    @SerialName("deadline") val deadline: String = "",
    @SerialName("difficultyLevel") val difficultyLevel: Int = 0,
    @SerialName("guideUrl") val guideUrl: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("isTeam") val isTeam: Boolean = false,
    @SerialName("maxPlayer") val maxPlayer: Int = 0,
    @SerialName("minPlayer") val minPlayer: Int = 0,
    @SerialName("needApproveCount") val needApproveCount: Int = 0,
    @SerialName("needLevel") val needLevel: Int = 0,
    @SerialName("questDescription") val questDescription: String = "",
    @SerialName("questName") val questName: String = "",
    @SerialName("questStateInfo") val questStateInfo: QuestStateInfo = QuestStateInfo(),
    @SerialName("questType") val questType: String = "",
    @SerialName("rewardExp") val rewardExp: Int = 0,
    @SerialName("rewardPoint") val rewardPoint: Int = 0,
    @SerialName("sortSeq") val sortSeq: Int = 0,
    @SerialName("trainingDescription") val trainingDescription: String = "",
    @SerialName("trainingType") val trainingType: String = "",
    @SerialName("thumbnailUrl") val thumbnailUrl: String = ""
)

fun String.convertType(): String {
    return when (this) {
        "CORE" -> "코어"
        "LOWER_BODY" -> "하체"
        "SQUATS" -> "스쿼트"
        "DEADLIFT" -> "데드"
        "LUNGES" -> "런지"
        else -> ""
    }
}