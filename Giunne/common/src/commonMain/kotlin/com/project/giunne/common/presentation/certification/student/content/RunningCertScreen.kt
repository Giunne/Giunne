package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.dummy.runningDoneList
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_running
import org.jetbrains.compose.resources.painterResource

@Composable
fun RunningCertScreen(
    modifier: Modifier = Modifier,
    onCertButtonClicked: () -> Unit,
    step: Int?
) {
    //// test ////
    var image by remember { mutableStateOf<Boolean?>(null) }
    //////////////

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (step != null) {
            RunningCertProgressBox(
                modifier = Modifier
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(262.gdp),
                weekText = "2주차",
                progressText = "선생님이 확인중이에요!",
                icon = {
                    Image(
                        modifier = Modifier.size(40.gdp),
                        painter = painterResource(Res.drawable.icon_running),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.ButtonBlack)
                    )
                },
                step = step,
            )
        } else {
            RunningCertBox(
                modifier = Modifier
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(262.gdp),
                weekText = "2주차", /* TODO API */
                image = image,
                onUploadButtonClicked = { image = true }, /* TODO API */
                onCertButtonClicked = { onCertButtonClicked() },
            )
        }
        SpH(10.gdp)
        DoneListBox(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .fillMaxWidth()
                .padding(vertical = 8.gdp, horizontal = 4.gdp),
            content = {
                LazyColumn{
                    items(
                        count = runningDoneList.size
                    ) {
                        RunningDoneListItemRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(68.gdp),
                            doneItem = runningDoneList[it]
                        )
                        SpH(4.gdp)
                    }
                }
            }
        )
    }
}