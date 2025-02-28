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
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.util.DataThrowable
import org.jetbrains.compose.resources.DrawableResource

val localCharacterList = listOf(
    CharacterUiState(1, Res.drawable.character_cat_level_1, "고양이"),
    CharacterUiState(8, Res.drawable.character_bear_level_1, "곰"),
    CharacterUiState(15, Res.drawable.character_dino_level_1, "공룡"),
    CharacterUiState(22, Res.drawable.character_raccoon_level_1, "너구리"),
    CharacterUiState(29, Res.drawable.character_rop_rabbit_level_1, "롭이어토끼"),
    CharacterUiState(36, Res.drawable.character_fox_level_1, "여우"),
    CharacterUiState(43, Res.drawable.character_duck_level_1, "오리"),
    CharacterUiState(50, Res.drawable.character_corgi_level_1, "웰시코기"),
    CharacterUiState(57, Res.drawable.character_rabbit_level_1, "토끼"),
    CharacterUiState(64, Res.drawable.character_penguin_level_1, "펭귄"),
    CharacterUiState(71, Res.drawable.character_poodle_level_1, "푸들"),
    CharacterUiState(78, Res.drawable.character_tiger_level_1, "호랑이"),
)

data class CharacterUiState(
    val id: Int,
    val res: DrawableResource,
    val name: String
)

data class SelectCharacterUiState(
    val isLoading: Boolean = false,
    val characterList: List<CharacterUiState> = localCharacterList,
    val avatarResponse: AvatarResponse = AvatarResponse(),
    val error: DataThrowable? = null
)

sealed interface SelectCharacterEvent {
    data class Success(val message: String) : SelectCharacterEvent
}