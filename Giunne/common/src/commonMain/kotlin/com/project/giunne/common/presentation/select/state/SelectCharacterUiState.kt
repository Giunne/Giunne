package com.project.giunne.common.presentation.select.state

import com.project.giunne.Res
import com.project.giunne.character_bear_level_1
import com.project.giunne.character_cat_level_1
import com.project.giunne.character_corgi_level_1
import com.project.giunne.character_dino_level_1
import com.project.giunne.character_duck_level_1
import com.project.giunne.character_fox_level_1
import com.project.giunne.character_penguin_level_1
import com.project.giunne.character_poodle_level_1
import com.project.giunne.character_rabbit_level_1
import com.project.giunne.character_raccoon_level_1
import com.project.giunne.character_rop_rabbit_level_1
import com.project.giunne.character_tiger_level_1
import org.jetbrains.compose.resources.DrawableResource

data class SelectCharacterUiState(
    val res: DrawableResource,
    val name: String
)

val characterList = listOf(
    SelectCharacterUiState(Res.drawable.character_cat_level_1, "고양이"),
    SelectCharacterUiState(Res.drawable.character_bear_level_1, "곰"),
    SelectCharacterUiState(Res.drawable.character_dino_level_1, "공룡"),
    SelectCharacterUiState(Res.drawable.character_raccoon_level_1, "너구리"),
    SelectCharacterUiState(Res.drawable.character_rop_rabbit_level_1, "롭이어토끼"),
    SelectCharacterUiState(Res.drawable.character_fox_level_1, "여우"),
    SelectCharacterUiState(Res.drawable.character_duck_level_1, "오리"),
    SelectCharacterUiState(Res.drawable.character_corgi_level_1, "웰시코기"),
    SelectCharacterUiState(Res.drawable.character_rabbit_level_1, "토끼"),
    SelectCharacterUiState(Res.drawable.character_penguin_level_1, "펭귄"),
    SelectCharacterUiState(Res.drawable.character_poodle_level_1, "푸들"),
    SelectCharacterUiState(Res.drawable.character_tiger_level_1, "호랑이"),
)