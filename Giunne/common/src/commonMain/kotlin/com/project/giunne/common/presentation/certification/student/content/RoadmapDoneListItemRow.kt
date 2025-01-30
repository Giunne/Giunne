package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.dummy.RoadmapDoneDto
import com.project.giunne.common.presentation.common.shape.GPSquircleBorderShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_plus
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoadmapDoneListItemRow(
    modifier: Modifier = Modifier,
    doneItem: RoadmapDoneDto
) {
    Row(
        modifier = modifier
            .background(
                color = GPColor.White,
                shape = RoundedCornerShape(12.gdp)
            )
            .border(
                width = 1.gdp,
                color = GPColor.BackgroundGray_F6F6F6,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(start = 8.gdp, end = 12.gdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleBorderShape(
            modifier = Modifier.size(52.gdp),
            backgroundColor = GPColor.BackgroundLightGray,
            borderColor = GPColor.BackgroundGray_F6F6F6,
            borderWidth = 1.gdp,
            content = {
                Image(
                    modifier = Modifier.size(32.gdp),
                    painter = painterResource(doneItem.img),
                    contentDescription = null
                )
            }
        )
        SpW(8.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = doneItem.level + "단계 " + doneItem.name,
            textColor = GPColor.TextBlack,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
        Image(
            modifier = Modifier.size(12.gdp),
            painter = painterResource(Res.drawable.icon_plus),
            contentDescription = null
        )
        SpW(8.gdp)
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.End
        ) {
            GPText(
                modifier = Modifier.height(14.gdp),
                text = doneItem.exp.toString() + " exp",
                textColor = GPColor.MainOrangeColor,
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Bold,
            )
            GPText(
                modifier = Modifier
                    .height(14.gdp),
                text = doneItem.coin.toString() + " 코인",
                textColor = GPColor.MainOrangeColor,
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Bold,
            )
        }
    }
}