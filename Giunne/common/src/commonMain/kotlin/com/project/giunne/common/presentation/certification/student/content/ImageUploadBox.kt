package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.picker.PlatformFile

@Composable
expect fun ImageUploadBox(
    modifier: Modifier = Modifier,
    onUploadButtonClicked: () -> Unit,
    dragAndDropFile: (PlatformFile?) -> Unit
)