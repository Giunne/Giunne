package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.presentation.common.button.GPBackButton
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

enum class Action {
    MODIFY,
    DELETE
}

@Composable
fun TeacherNoticeDetailDialog(
    noticeData: NoticeResponse,
    onDismiss: () -> Unit,
    onConfirm: (action: Action, noticeId: Int, title: String, content: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf(noticeData.title) }
    var content by remember { mutableStateOf(noticeData.content) }
    val isEnabled by derivedStateOf {
        title.isNotEmpty() && content.isNotEmpty()
    }
    var action by remember { mutableStateOf(Action.DELETE) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                GPMainTopBar(
                    titleText = "공지사항 상세",
                    leftIcon = {
                        GPBackButton { onDismiss() }
                    },
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(GPColor.BackgroundLightGray)
                    .padding(16.gdp)
                    .verticalScroll(rememberScrollState()),
            ) {
                ModifyCheckBox(
                    modifier = Modifier.align(Alignment.End),
                    isChecked = action == Action.MODIFY,
                    onCheckedChanged = { check ->
                        action = if (check) Action.MODIFY else Action.DELETE
                    }
                )
                when (action) {
                    Action.MODIFY -> {
                        GPText(
                            text = "제목",
                            textSize = 18.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextGray
                        )

                        SpH(12.gdp)

                        NoticeTextField(
                            focusManager = focusManager,
                            placeholder = "공지사항 제목을 입력해주세요.",
                            text = title,
                            onTextChanged = {
                                title = it
                            }
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 16.gdp),
                            color = GPColor.BackgroundGray_EBEBEB
                        )

                        GPText(
                            text = "내용",
                            textSize = 18.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextGray
                        )

                        SpH(12.gdp)

                        NoticeTextField(
                            focusManager = focusManager,
                            placeholder = "공지사항 내용을 입력해주세요.",
                            text = content,
                            onTextChanged = {
                                content = it
                            }
                        )
                    }

                    Action.DELETE -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            GPText(
                                text = "제목",
                                textSize = 18.gsp,
                                fontFamily = GPFontFamily.Bold,
                                textColor = GPColor.TextGray
                            )

                            GPText(
                                text = noticeData.asTimeString,
                                textSize = 10.gsp,
                                fontFamily = GPFontFamily.Medium,
                                textColor = GPColor.ButtonLightGray
                            )
                        }

                        SpH(12.gdp)

                        GPText(
                            text = noticeData.title,
                            textSize = 16.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextBlack
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 16.gdp),
                            color = GPColor.BackgroundGray_EBEBEB
                        )

                        GPText(
                            text = "내용",
                            textSize = 18.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextGray
                        )

                        SpH(12.gdp)

                        GPText(
                            text = noticeData.content,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextBlack
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonLightGray,
                        pressColor = GPColor.ButtonPressLightGray,
                        hoverColor = GPColor.ButtonHoverLightGray,
                        onClick = onDismiss,
                    ) {
                        GPText(
                            text = "취소",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                    SpW(10.gdp)
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
                        pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonPressLightGray,
                        hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonHoverLightGray,
                        onClick = {
                            onConfirm(action, noticeData.id, title, content)
                        },
                    ) {
                        GPText(
                            text = when (action) {
                                Action.MODIFY -> "수정"
                                Action.DELETE -> "삭제"
                            },
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }
            }
        }
    }
}