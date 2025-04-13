package com.project.giunne.common.presentation.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.request.GachaType
import com.project.giunne.common.data.util.DefineUrl
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.content.LottieBox
import com.project.giunne.common.presentation.shop.content.MysteryBox
import com.project.giunne.common.presentation.shop.content.aRankColorBrush
import com.project.giunne.common.presentation.shop.content.bRankColorBrush
import com.project.giunne.common.presentation.shop.content.cRankColorBrush
import com.project.giunne.common.presentation.shop.content.sRankColorBrush
import com.project.giunne.common.presentation.shop.intent.GachaStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun PickingItemScreen(
    onWearingItemClick: () -> Unit = {},
    onRetryButtonClick: () -> Unit = {},
    gachaType: GachaType
) {
    var isProgress by remember { mutableStateOf(true) }
    val gachaStore by remember { mutableStateOf(GachaStore()) }
    val gachaState by gachaStore.uiState.collectAsState()

    LaunchedEffect(Unit) {
        delay(2000)
        isProgress = false
        when (gachaType) {
            GachaType.GENERAL -> { gachaStore.postGacha(GachaType.GENERAL.text) }
            GachaType.PREMIUM -> { gachaStore.postGacha(GachaType.PREMIUM.text) }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(84.gdp))

        if (isProgress) {
            MysteryBox(
                modifier = Modifier.wrapContentSize()
            )
        } else {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                LottieBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    filePath = "files/animation_party_background.json"
                )

                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.gdp)
                ) {
                    if (gachaState.randomItem.itemImages.isNotEmpty()) {
                        AsyncImage(
                            modifier = Modifier.size(120.gdp),
                            model = DefineUrl.IMAGE_BASE_URL + gachaState.randomItem.itemImages.first().fileUrl, // TODO 레벨 정보 Define에 넣어주고 분기
                            contentDescription = "이미지"
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.gdp)
                    ) {
                        GPAnnotatedText(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        brush = when (gachaState.randomItem.itemGrade) {
                                            "S" -> sRankColorBrush
                                            "A" -> aRankColorBrush
                                            "B" -> bRankColorBrush
                                            else -> cRankColorBrush
                                        },
                                        fontSize = 20.gsp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) {
                                    append(gachaState.randomItem.itemName)
                                }
                            },
                        )
                        GPText(
                            text = "당첨!",
                            textSize = 18.gsp,
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth()
                    .height(48.gdp),
            ) {
                GPButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = onWearingItemClick,
                ) {
                    GPText(
                        text = "착용하러 가기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                SpW(16.gdp)
                GPButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    normalColor = GPColor.ButtonBlack,
                    pressColor = GPColor.ButtonPressBlack,
                    hoverColor = GPColor.ButtonHoverBlack,
                    onClick = onRetryButtonClick,
                ) {
                    GPText(
                        text = "재도전!",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }

    if(gachaState.loading) {
        Loader()
    }
}