package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser
import javax.swing.SwingUtilities
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun VideoPicker(
    callback: (PlatformFile?) -> Unit,
) {
    val defaultPath = ""
    val title = "videoPicker"

    SwingUtilities.invokeLater {
        val frame = Frame()
        val fileChooser = JFileChooser(defaultPath).apply {
            dialogTitle = title
            fileSelectionMode = JFileChooser.FILES_ONLY
            fileFilter = FileNameExtensionFilter("image Files", "mp4", "mp3", "avi", "mov")
            isMultiSelectionEnabled = false
        }

        val result = fileChooser.showOpenDialog(frame)
        frame.dispose()

        if (result == JFileChooser.APPROVE_OPTION) {
            callback(PlatformFile(fileChooser.selectedFile))
        } else {
            callback(null)
        }
    }
}