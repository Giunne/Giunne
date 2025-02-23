package com.project.giunne.common.presentation.common.dropdown

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.giunne.Res
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_dropdown_close
import com.project.giunne.icon_dropdown_expanded
import org.jetbrains.compose.resources.painterResource

@Composable
fun GPDropdownMenu(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "Dropdown Transition")
    var calculateWidth by remember { mutableIntStateOf(0) } // 메뉴 선택 옵션의 가로 크기를 저장
    var calculateHeight by remember { mutableIntStateOf(0) } // 메뉴 선택 옵션의 세로 크기를 저장
    val menuWidth = with(LocalDensity.current) { calculateWidth.toDp() }
    val menuHeight = with(LocalDensity.current) { calculateHeight.toDp() }

    // 애니메이션 크기 조절을 위한 width 값
    val height by transition.animateDp(
        label = "Menu Width",
        transitionSpec = { tween(durationMillis = 300) }
    ) { if (it) menuHeight.times(options.size) else 0.gdp }

    Column(
        modifier = modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // 선택된 옵션 버튼
        Row(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(12.gdp))
                .border(1.gdp, GPColor.BorderLightGray, shape = RoundedCornerShape(12.gdp))
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    calculateWidth = coordinates.size.width
                    calculateHeight = coordinates.size.height
                }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = if (expanded) {
                    painterResource(Res.drawable.icon_dropdown_close)
                } else {
                    painterResource(Res.drawable.icon_dropdown_expanded)
                },

                contentDescription = "Dropdown Arrow"
            )
        }
        // 드롭다운 메뉴

        Box(
            modifier = Modifier
                .width(menuWidth)
                .height(height.coerceAtMost(200.gdp))
                .offset(y = 4.gdp)
                .background(GPColor.White, shape = RoundedCornerShape(12.gdp))
                .border(1.gdp, GPColor.BorderLightGray, shape = RoundedCornerShape(12.gdp))
                .clip(RoundedCornerShape(12.gdp))
                .animateContentSize()
        ) {
            if (expanded) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    options.forEach { option ->
                        Box(
                            modifier = Modifier
                                .width(menuWidth)
                                .height(menuHeight)
                                .clickable {
                                    onOptionSelected(option)
                                    expanded = false
                                }
                                .padding(start = 8.gdp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = option,
                                fontSize = 12.gsp,
                            )
                        }
                    }
                }
            }
        }
    }
}