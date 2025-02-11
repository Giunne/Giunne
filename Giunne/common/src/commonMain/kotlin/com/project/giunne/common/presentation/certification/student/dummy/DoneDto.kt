package com.project.giunne.common.presentation.certification.student.dummy

import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.roadcon_1_birddog
import com.project.giunne.roadcon_2_deadbug
import com.project.giunne.roadcon_3_beast
import com.project.giunne.roadcon_4_a_plank
import org.jetbrains.compose.resources.DrawableResource

data class RoadmapDoneDto(
    val img: DrawableResource,
    val level: String,
    val name: String,
    val exp: Int,
    val coin: Int,
)

data class RunningDoneDto(
    val date: String,
    val exp: Int,
    val coin: Int,
)

val roadmapDoneList = listOf(
    RoadmapDoneDto(Res.drawable.roadcon_1_birddog, "1", "버드독", 10, 2),
    RoadmapDoneDto(Res.drawable.roadcon_2_deadbug, "2", "데드버그", 12, 3),
    RoadmapDoneDto(Res.drawable.roadcon_3_beast, "3", "비스트", 14, 4),
    RoadmapDoneDto(Res.drawable.roadcon_4_a_plank, "4-a", "플랭크", 16, 5),
)

val runningDoneList = listOf(
    RunningDoneDto("25.01.02", 4, 1),
    RunningDoneDto("25.01.03", 4, 1),
    RunningDoneDto("25.01.05", 4, 1),
    RunningDoneDto("25.01.08", 4, 1),
)