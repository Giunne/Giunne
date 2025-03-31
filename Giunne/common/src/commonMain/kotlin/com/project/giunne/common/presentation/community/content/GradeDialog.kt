package com.project.giunne.common.presentation.community.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.graphics.shapes.star
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_check
import org.jetbrains.compose.resources.painterResource

@Composable
fun GradeDialog(
    questName: String,
    currentApproveTitle: String,
    isLastApprove: Boolean,
    onCloseButtonClicked: () -> Unit,
    onConfirmButtonClicked: (Int, Boolean, Boolean) -> Unit,
) {
    var grade by remember { mutableStateOf(0) }
    var isChecked by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (isChecked) GPColor.BackgroundFrameOrange else GPColor.BackgroundGray_F6F6F6
    )

    Dialog(
        onDismissRequest = { onCloseButtonClicked() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .width(320.gdp)
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                )
                .padding(vertical = 8.gdp, horizontal = 10.gdp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SpH(20.gdp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPText(
                    text = "$questName ",
                    textColor = GPColor.MainOrangeColor,
                    textSize = 18.gsp,
                    fontFamily = GPFontFamily.Bold
                )
                GPText(
                    text = "채점 $currentApproveTitle",
                    textColor = GPColor.TextBlack,
                    textSize = 18.gsp,
                    fontFamily = GPFontFamily.Bold
                )
            }
            SpH(10.gdp)
            if (isLastApprove) {
                Row(
                    modifier = Modifier
                        .height(50.gdp)
                        .background(GPColor.White),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarShape(
                        isCheck = grade >= 1,
                        onClick = { grade = 1 },
                    )
                    Spacer(modifier = Modifier.width(10.gdp))
                    StarShape(
                        isCheck = grade >= 2,
                        onClick = { grade = 2 },
                    )
                    Spacer(modifier = Modifier.width(10.gdp))
                    StarShape(
                        isCheck = grade >= 3,
                        onClick = { grade = 3 },
                    )
                }
                SpH(10.gdp)
                Row(
                    modifier = Modifier
                        .noRippleClickable { isChecked = !isChecked },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.gdp)
                            .background(
                                color = animatedColor,
                                shape = RoundedCornerShape(12.gdp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isChecked) {
                            Image(
                                modifier = Modifier.size(11.gdp),
                                painter = painterResource(Res.drawable.icon_check),
                                contentDescription = null,
                            )
                        }
                    }
                    SpW(10.gdp)
                    GPText(
                        text = "추가 동작 여부",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.TextBlack
                    )
                }
            }
            SpH(20.gdp)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.gdp),
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonPressLightGray,
                    hoverColor = GPColor.ButtonHoverLightGray,
                    onClick = { onCloseButtonClicked() },
                ) {
                    GPText(
                        text = "닫기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                SpW(10.gdp)
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { onConfirmButtonClicked(grade, isChecked, false) },
                ) {
                    GPText(
                        text = "실패",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                SpW(10.gdp)
                GPButton(
                    modifier = Modifier
                        .weight(2f)
                        .height(44.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { onConfirmButtonClicked(grade, isChecked, true) },
                ) {
                    GPText(
                        text = if (isLastApprove) "채점하기" else "통과하기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}

val starColor = Brush.linearGradient(
    colors = listOf(
        GPColor.ARankItemColor1,
        GPColor.ARankItemColor2
    )
)

@Composable
fun StarShape(
    modifier: Modifier = Modifier,
    isCheck: Boolean,
    onClick: () -> Unit
) {
    val shapeA = remember {
        RoundedPolygon.circle()
    }
    val shapeB = remember {
        RoundedPolygon.star(
            5,
            rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val animatedProgress = animateFloatAsState(
        targetValue = if (isCheck) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    val animatedColor by animateColorAsState(
        targetValue = if (isCheck) GPColor.MainOrangeColor else GPColor.TextGray,
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )


    Box(
        modifier = Modifier
            .size(40.gdp)
            .padding(8.gdp)
            .clip(
                MorphPolygonShape(morph, animatedProgress.value)
            )
            .background(
//                color = animatedColor
                brush = if (isCheck) starColor else Brush.linearGradient(listOf(GPColor.ButtonLightGray, GPColor.ButtonLightGray))
            )
            .size(40.gdp)
            .noRippleClickable (interactionSource = interactionSource) {
                onClick()
            }
    )
}

class MorphPolygonShape(
    private val morph: Morph,
    private val percentage: Float
) : Shape {
    private val matrix = Matrix()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Below assumes that you haven't changed the default radius of 1f, nor the centerX and centerY of 0f
        // By default this stretches the path to the size of the container, if you don't want stretching, use the same size.width for both x and y.
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)
        matrix.rotateZ(55f)

        val path = morph.toComposePath(progress = percentage)
        path.transform(matrix)
        return Outline.Generic(path)
    }
}

fun Morph.toComposePath(progress: Float, scale: Float = 1f, path: Path = Path()): Path {
    var first = true
    path.rewind()
    forEachCubic(progress) { bezier ->
        if (first) {
            path.moveTo(bezier.anchor0X * scale, bezier.anchor0Y * scale)
            first = false
        }
        path.cubicTo(
            bezier.control0X * scale, bezier.control0Y * scale,
            bezier.control1X * scale, bezier.control1Y * scale,
            bezier.anchor1X * scale, bezier.anchor1Y * scale
        )
    }
    path.close()
    return path
}

fun List<Cubic>.toPath(path: Path = Path(), scale: Float = 1f): Path {
    path.rewind()
    firstOrNull()?.let { first ->
        path.moveTo(first.anchor0X * scale, first.anchor0Y * scale)
    }
    for (bezier in this) {
        path.cubicTo(
            bezier.control0X * scale, bezier.control0Y * scale,
            bezier.control1X * scale, bezier.control1Y * scale,
            bezier.anchor1X * scale, bezier.anchor1Y * scale
        )
    }
    path.close()
    return path
}