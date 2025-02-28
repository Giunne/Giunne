package com.project.giunne.common.presentation.community.student.dummy

import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.test_character
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

val roadmapCommunityList = listOf(
    CommunityDto("공룡이", Res.drawable.test_character,"1단계 버드독", 1, "25.01.01", CertPage.RoadMap),
    CommunityDto("공룡이", Res.drawable.test_character,"2단계 데드버그", 2, "25.01.02", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"1단계 버드독", 5, "25.01.03", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"2단계 데드버그", 4, "25.01.04", CertPage.RoadMap),
    CommunityDto("간달프", Res.drawable.test_character,"1단계 버드독", 8, "25.01.05", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"12-b단계 익스프레스 레그", 4, "25.01.02", CertPage.RoadMap),
    CommunityDto("간달프", Res.drawable.test_character,"1단계 버드독", 8, "25.01.03", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"2단계 데드버그", 4, "25.01.12", CertPage.RoadMap),
    CommunityDto("간달프", Res.drawable.test_character,"1단계 버드독", 8, "25.01.22", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"2단계 데드버그", 4, "25.02.02", CertPage.RoadMap),
    CommunityDto("간달프", Res.drawable.test_character,"1단계 버드독", 8, "25.01.13", CertPage.RoadMap),
    CommunityDto("금쪽이", Res.drawable.test_character,"2단계 데드버그", 4, "25.01.06", CertPage.RoadMap),
    CommunityDto("간달프", Res.drawable.test_character,"1단계 버드독", 8, "25.01.08", CertPage.RoadMap),
)

val runningCommunityList = listOf(
    CommunityDto("공룡이", Res.drawable.test_character,"1주차-1", 1, "25.01.01", CertPage.Running, "러닝"),
    CommunityDto("공룡이", Res.drawable.test_character,"1주차-2", 2, "25.01.02", CertPage.Running, "러닝"),
    CommunityDto("금쪽이", Res.drawable.test_character,"2주차-1", 5, "25.01.03", CertPage.Running, "러닝"),
    CommunityDto("금쪽이", Res.drawable.test_character,"2주차-2", 4, "25.01.04", CertPage.Running, "러닝"),
    CommunityDto("간달프", Res.drawable.test_character,"3주차-1", 8, "25.01.05", CertPage.Running, "러닝"),
)

@Serializable
data class CommunityDto(
    val name: String,
    @Contextual val character: DrawableResource,
    val content: String,
    val commentCount: Int,
    val date: String = "25.01.02",
    val type: CertPage,
    val rootName: String = "코어",
)