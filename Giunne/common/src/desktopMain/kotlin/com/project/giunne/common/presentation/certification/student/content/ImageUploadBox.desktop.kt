package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.awtTransferable
import com.project.giunne.common.presentation.common.picker.PlatformFile
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import java.awt.datatransfer.DataFlavor
import java.io.File

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
actual fun ImageUploadBox(
    modifier: Modifier,
    onUploadButtonClicked: () -> Unit,
    dragAndDropFile: (PlatformFile?) -> Unit
) {
    var droppedFiles by remember { mutableStateOf<List<File?>?>(null) }
    var droppedFile by remember { mutableStateOf<File?>(null) }

    var isOnBox by remember { mutableStateOf(false) }

    val fillColor by animateColorAsState(
        targetValue = if (isOnBox) GPColor.ButtonPressWhite else GPColor.BackgroundGray_F6F6F6
    )

    val dragAndDropTarget = remember {
        object: DragAndDropTarget {
            // Highlights the border of a potential drop target
            override fun onEntered(event: DragAndDropEvent) { isOnBox = true }
            override fun onExited(event: DragAndDropEvent) { isOnBox = false }
            override fun onEnded(event: DragAndDropEvent) { isOnBox = false }
            override fun onDrop(event: DragAndDropEvent): Boolean {
                event.awtTransferable.let {
                    println("event : $event")
                    if (it.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        val files = it.getTransferData(DataFlavor.javaFileListFlavor) as List<File>
                        if (files.isNotEmpty()) {
                            droppedFiles = files
                            droppedFile = files.first()
                        }
                    }
                }
                return true
            }
        }
    }

    Column(
        modifier = modifier
            .dragAndDropTarget(
                shouldStartDragAndDrop = { true },
                target = dragAndDropTarget
            )
            .background(
                color = fillColor,
                shape = RoundedCornerShape(12.gdp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            GPText(
                text = "인증에 필요한 ",
                textColor = GPColor.TextBlack,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold
            )
            GPText(
                text = "사진",
                textColor = GPColor.MainOrangeColor,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold
            )
            GPText(
                text = "을 촬영해 올려주세요",
                textColor = GPColor.TextBlack,
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold
            )
        }
        SpH(6.gdp)
        GPText(
            text = "선생님이 확인해줄거에요!",
            textColor = GPColor.TextBlack,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
    }

    with(droppedFile) {
        if (this != null) {
            dragAndDropFile(PlatformFile(droppedFile))
        }
    }
}