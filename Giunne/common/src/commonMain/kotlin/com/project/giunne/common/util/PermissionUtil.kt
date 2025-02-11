package com.project.giunne.common.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PermissionController(
    modifier: Modifier = Modifier,
    needPackageInstallPermission: () -> Unit = {}
)