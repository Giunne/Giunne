package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.data.remote.response.QuestStateInfo
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapState
import com.project.giunne.common.presentation.shop.content.EmptyItemList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun TeacherCheckStudentDialog(
    modifier: Modifier = Modifier,
    roadMapState: RoadMapState,
    dismissSuccessDialog: () -> Unit,
    onAllSelectedChange: (Boolean) -> Unit,
    studentList: List<QuestStateInfo>,
    onDismissDialog: () -> Unit,
    onCheckedChanged: (QuestStateInfo, Boolean) -> Unit,
    onConfirm: () -> Unit
) {
    val isAllSelected by derivedStateOf {
        studentList.all { it.isChecked }
    }
    val isNoneSelected by derivedStateOf {
        studentList.none { it.isChecked }
    }

    if (roadMapState.isSuccess) {
        GPAlertDialog(
            title = "학생 로드맵",
            content = "성공적으로 체크되었습니다!",
            dismiss = dismissSuccessDialog
        )
    }
    Dialog(
        onDismissRequest = onDismissDialog,
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.gdp))
            GPText(
                text = "운동 상황 체크",
                textSize = 18.gsp
            )

            Spacer(modifier = Modifier.height(16.gdp))

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.gdp),
                color = GPColor.BorderLightGray
            )

            Row {
                Spacer(modifier = Modifier.weight(1f))
                CheckboxAll(
                    isChecked = isAllSelected,
                    onCheckedChanged = {
                        onAllSelectedChange(it)
                    }
                )
            }

            if (studentList.isEmpty()) {
                EmptyItemList(
                    modifier = Modifier.fillMaxWidth()
                        .heightIn(200.gdp, 400.gdp),
                    description = "확인할 학생들이 없습니다."
                )
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .heightIn(200.gdp, 400.gdp)
                ) {
                    items(studentList) { student ->
                        StudentCheckboxRow(
                            modifier = Modifier.fillMaxWidth(),
                            studentCheck = student,
                            onCheckedChanged = { studentCheck, check ->
                                onCheckedChanged(studentCheck, check)
                            }
                        )
                    }
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.gdp)
            ) {
                GPButton(
                    modifier = Modifier
                        .height(48.gdp)
                        .weight(1f),
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonPressLightGray,
                    hoverColor = GPColor.ButtonHoverLightGray,
                    onClick = onDismissDialog,
                ) {
                    GPText(
                        text = "닫기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.TextBlack
                    )
                }

                GPButton(
                    modifier = Modifier
                        .height(48.gdp)
                        .weight(1f),
                    normalColor = if (isNoneSelected) GPColor.ButtonLightGray else GPColor.ButtonOrange,
                    pressColor = if (isNoneSelected) GPColor.ButtonPressLightGray else GPColor.ButtonPressOrange,
                    hoverColor = if (isNoneSelected) GPColor.ButtonHoverLightGray else GPColor.ButtonHoverOrange,
                    onClick = {
                        if (!isNoneSelected) {
                            onConfirm()
                        }
                    },
                ) {
                    GPText(
                        text = "통과",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = if (isNoneSelected) GPColor.TextBlack else GPColor.White
                    )
                }
            }
        }
    }
}