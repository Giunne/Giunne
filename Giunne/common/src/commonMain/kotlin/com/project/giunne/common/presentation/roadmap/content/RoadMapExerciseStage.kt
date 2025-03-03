package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShapeWithBorder
import com.project.giunne.common.presentation.roadmap.node.ConnectNode
import com.project.giunne.common.presentation.roadmap.node.Node
import com.project.giunne.common.presentation.roadmap.node.NodeStatus
import com.project.giunne.common.presentation.roadmap.state.ExerciseUiState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_lock
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoadMapExerciseStage(
    offset: Offset,
    exerciseList: List<ExerciseUiState>,
    node: List<Node>,
    connect: List<ConnectNode>,
    onExerciseClicked: (String) -> Unit,
) {
    val density = LocalDensity.current.density
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(offset.x.dp, offset.y.dp),
    ) {
        DrawExerciseLine(
            density = density,
            exerciseState = exerciseList,
            connect = connect
        )
        node.forEach { node ->
            val findNode = exerciseList.find { it.step == node.step }

            val borderColor = if (node.boxSize == 30f) {
                GPColor.MainOrangeColor
            } else if (findNode?.status == NodeStatus.CONFIRM) {
                GPColor.Green
            } else if (findNode?.isBonusDay == true) {
                GPColor.MainOrangeColor
            } else {
                GPColor.ButtonLightGray
            }
            val backgroundColor = if (node.boxSize == 30f) {
                GPColor.MainOrangeColor
            } else if (findNode?.status == NodeStatus.LOCK || findNode?.status == NodeStatus.UNCHECK) {
                GPColor.TextBlack
            } else {
                GPColor.White
            }
            Box (
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                GPSquircleShapeWithBorder(
                    modifier = Modifier
                        .size(node.boxSize.dp)
                        .offset(node.drawOffset.x.dp, node.drawOffset.y.dp)
                        .noRippleClickable {
                            if (findNode?.status != NodeStatus.LOCK) {
                                onExerciseClicked(node.step)
                            }
                        },
                    backgroundColor = backgroundColor,
                    borderColor = borderColor
                ) {
                    Text(
                        text = node.step
                    )
                }
                if (findNode?.status == NodeStatus.LOCK) {
                    Icon(
                        modifier = Modifier
                            .size(18.gdp)
                            .offset(node.drawOffset.x.dp, node.drawOffset.y.dp),
                        painter = painterResource(Res.drawable.icon_lock),
                        contentDescription = "잠금",
                        tint = GPColor.MainOrangeColor
                    )
                }
            }
        }
    }
}