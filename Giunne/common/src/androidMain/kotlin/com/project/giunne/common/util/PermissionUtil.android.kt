package com.project.giunne.common.util

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

private const val TAG = "PermissionUtil.android"
@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun PermissionController(
    modifier: Modifier,
    needPackageInstallPermission: () -> Unit
) {
    val permissions = rememberMultiplePermissionsState(
        permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
//                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                Manifest.permission.REQUEST_INSTALL_PACKAGES
            )
        else listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES
        )
    )

    LaunchedEffect(Unit) {
        if (!permissions.allPermissionsGranted) {
            GLog.d(TAG, "권한 받야져야 함.")
            permissions.launchMultiplePermissionRequest()
        }

//        ResourceUtil.packageInstallerPermission(needPackageInstallPermission)
    }
}