package com.project.giunne.common.util

import androidx.compose.material3.SnackbarHostState

fun callApiWithSnackbarDismiss(
    snackbarHostState: SnackbarHostState,
    callApi: () -> Unit
) {
    snackbarHostState.currentSnackbarData?.dismiss()
    callApi()
}