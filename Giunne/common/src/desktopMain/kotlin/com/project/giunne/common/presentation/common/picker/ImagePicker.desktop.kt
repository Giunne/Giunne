package com.project.giunne.common.presentation.common.picker

import androidx.compose.runtime.Composable
import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser
import javax.swing.SwingUtilities
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun ImagePicker(
    callback: (PlatformFile?) -> Unit
) {
    val defaultPath = ""
    val title = "imagePicker"

    SwingUtilities.invokeLater {
        val frame = Frame()
        val fileChooser = JFileChooser(defaultPath).apply {
            dialogTitle = title
            fileSelectionMode = JFileChooser.FILES_ONLY
            fileFilter = FileNameExtensionFilter("image Files", "png", "jpg", "jpeg", "bmp")
            isMultiSelectionEnabled = false
        }

        val result = fileChooser.showOpenDialog(frame)
        frame.dispose()

        if (result == JFileChooser.APPROVE_OPTION) {
//            callback(fileChooser.selectedFile)
            callback(PlatformFile(fileChooser.selectedFile))
        } else {
            callback(null)
        }
    }
}