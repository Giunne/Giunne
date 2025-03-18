package com.project.giunne.common.util

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.finishAffinity

@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit
): Unit = androidx.activity.compose.BackHandler(enabled, onBack)

actual fun exitProgram() {

}