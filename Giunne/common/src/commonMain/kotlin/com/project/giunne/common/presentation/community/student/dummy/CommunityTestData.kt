package com.project.giunne.common.presentation.community.student.dummy

import com.project.giunne.Res
import com.project.giunne.test_character
import org.jetbrains.compose.resources.DrawableResource

val roadmapCommunityList = listOf(
    Community("공룡이", Res.drawable.test_character,"1단계 버드독", 1),
    Community("공룡이", Res.drawable.test_character,"2단계 데드버그", 2),
    Community("금쪽이", Res.drawable.test_character,"1단계 버드독", 5),
    Community("금쪽이", Res.drawable.test_character,"2단계 데드버그", 4),
    Community("간달프", Res.drawable.test_character,"1단계 버드독", 8),
)

val runningCommunityList = listOf(
    Community("공룡이", Res.drawable.test_character,"1주차-1", 1),
    Community("공룡이", Res.drawable.test_character,"1주차-2", 2),
    Community("금쪽이", Res.drawable.test_character,"2주차-1", 5),
    Community("금쪽이", Res.drawable.test_character,"2주차-2", 4),
    Community("간달프", Res.drawable.test_character,"3주차-1", 8),
)

data class Community(
    val name: String,
    val character: DrawableResource,
    val content: String,
    val commentCount: Int,
)