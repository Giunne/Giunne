package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.WearingItem
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun FriendAvatarDialog(
    modifier: Modifier = Modifier,
    wearingItems: List<WearingItem>,
    onDismiss: () -> Unit
) {

    val characterItem = wearingItems.find { it.categoryId == 6 } ?: wearingItems.find { it.categoryId == 1 }
    val characterUrl = characterItem?.itemImage?.fileUrl.orEmpty()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    modifier = Modifier.size(256.gdp),
                    model = IMAGE_BASE_URL + characterUrl,
                    contentDescription = null
                )
                wearingItems.filter { it.categoryId != 1 && it.categoryId != 6 }.forEach { item ->
                    // 이미지 크기만 측정 -> 추후 서버에 이미지 크기도 함께 저장하도록 수정
                    var itemSize by remember { mutableStateOf(IntSize.Zero) }

                    SubcomposeLayout { constraints ->

                        val unconstrainedConstraints = constraints.copy(
                            minWidth = 0,
                            minHeight = 0,
                            maxWidth = Int.MAX_VALUE,
                            maxHeight = Int.MAX_VALUE
                        )

                        subcompose("measure") {
                            AsyncImage(
                                modifier = Modifier.onSizeChanged {
                                    itemSize = it
                                }
                                    .alpha(0f),
                                model = IMAGE_BASE_URL + item.itemImage.fileUrl,
                                contentDescription = null
                            )
                        }.first().measure(unconstrainedConstraints)

                        val contentMeasure = subcompose("content") {
                            AsyncImage(
                                modifier = Modifier
                                    .size(
                                        width = (itemSize.width / 4).gdp,
                                        height = (itemSize.height / 4).gdp
                                    )
                                    .offset(
                                        x = item.itemImage.itemImagePosition?.positionX?.gdp ?: 0.gdp,
                                        y = item.itemImage.itemImagePosition?.positionY?.gdp ?: 0.gdp
                                    ),
                                model = IMAGE_BASE_URL + item.itemImage.fileUrl,
                                contentDescription = null
                            )
                        }.first().measure(constraints)


                        if (item.categoryId != 6) {
                            layout(contentMeasure.width, contentMeasure.height) {
                                contentMeasure.place(0, 0)
                            }
                        } else {
                            layout(0, 0) {}
                        }
                    }
                }
            }
            GPButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = onDismiss,
            ) {
                GPText(
                    text = "닫기",
                    textSize = 16.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}