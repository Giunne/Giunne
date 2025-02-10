package com.project.giunne.common.presentation.common.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.project.giunne.common.presentation.common.noRippleClickable

@Composable
actual fun ImageViewer(
    modifier: Modifier,
    dismiss: () -> Unit,
    imagePath: String
) {
    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
//        Card(
//            modifier = Modifier
//        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        dismiss()
                    }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = imagePath,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
            }
        }
//    }
}