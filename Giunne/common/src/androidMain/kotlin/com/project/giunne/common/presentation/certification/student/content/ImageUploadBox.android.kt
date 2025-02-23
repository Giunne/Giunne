package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.picker.PlatformFile
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_upload_image
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun ImageUploadBox(
    modifier: Modifier,
    onUploadButtonClicked: () -> Unit,
    dragAndDropFile: (PlatformFile?) -> Unit
) {
    Column(
        modifier = Modifier
            .height(130.gdp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GPIconButton(
            modifier = Modifier.size(72.gdp),
            icon = {
                Image(
                    modifier = Modifier.size(52.gdp),
                    painter = painterResource(Res.drawable.icon_upload_image),
                    contentDescription = null,
                )
            },
            normalColor = GPColor.White,
            pressColor = GPColor.ButtonPressWhite,
            onClick = {
                onUploadButtonClicked()
            },
        )
        SpH(16.gdp)
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
            text = "선생님이 확인해줄거에요!", /* TODO String */
            textColor = GPColor.TextBlack,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
    }
}