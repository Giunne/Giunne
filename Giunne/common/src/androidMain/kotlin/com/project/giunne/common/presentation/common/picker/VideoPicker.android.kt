package com.project.giunne.common.presentation.common.picker

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import java.io.File

@Composable
actual fun VideoPicker(
    callback: (PlatformFile?) -> Unit
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            callback(
                if (uri != null) PlatformFile(uri)
                else null
            )
        }
    )

    LaunchedEffect(Unit) {
        delay(100)
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.VideoOnly
            )
        )
    }
}