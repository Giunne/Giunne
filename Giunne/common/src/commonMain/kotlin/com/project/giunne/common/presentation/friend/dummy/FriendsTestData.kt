package com.project.giunne.common.presentation.friend.dummy

import com.project.giunne.Res
import com.project.giunne.character_bear_level_1
import com.project.giunne.character_bear_level_2
import com.project.giunne.character_bear_level_3
import com.project.giunne.character_bear_level_4
import com.project.giunne.character_bear_level_5
import com.project.giunne.character_bear_level_6
import com.project.giunne.character_bear_level_7
import com.project.giunne.character_cat_level_1
import com.project.giunne.character_cat_level_2
import com.project.giunne.character_cat_level_3
import com.project.giunne.character_cat_level_4
import com.project.giunne.character_cat_level_5
import com.project.giunne.character_cat_level_6
import com.project.giunne.character_cat_level_7
import com.project.giunne.character_corgi_level_1
import com.project.giunne.character_corgi_level_2
import com.project.giunne.character_corgi_level_3
import com.project.giunne.character_corgi_level_4
import com.project.giunne.character_corgi_level_5
import com.project.giunne.character_corgi_level_6
import com.project.giunne.character_corgi_level_7
import com.project.giunne.character_dino_level_1
import com.project.giunne.character_dino_level_2
import com.project.giunne.character_dino_level_3
import com.project.giunne.character_dino_level_4
import com.project.giunne.character_dino_level_5
import com.project.giunne.character_dino_level_6
import com.project.giunne.character_dino_level_7
import com.project.giunne.character_duck_level_1
import com.project.giunne.character_duck_level_2
import com.project.giunne.character_duck_level_3
import com.project.giunne.character_duck_level_4
import com.project.giunne.character_duck_level_5
import com.project.giunne.character_duck_level_6
import com.project.giunne.character_duck_level_7
import com.project.giunne.character_fox_level_1
import com.project.giunne.character_fox_level_2
import com.project.giunne.character_fox_level_3
import com.project.giunne.character_fox_level_4
import com.project.giunne.character_fox_level_5
import com.project.giunne.character_fox_level_6
import com.project.giunne.character_fox_level_7
import com.project.giunne.character_penguin_level_1
import com.project.giunne.character_penguin_level_2
import com.project.giunne.character_penguin_level_3
import com.project.giunne.character_penguin_level_4
import com.project.giunne.character_penguin_level_5
import com.project.giunne.character_penguin_level_6
import com.project.giunne.character_penguin_level_7
import com.project.giunne.character_poodle_level_1
import com.project.giunne.character_poodle_level_2
import com.project.giunne.character_poodle_level_3
import com.project.giunne.character_poodle_level_4
import com.project.giunne.character_poodle_level_5
import com.project.giunne.character_poodle_level_6
import com.project.giunne.character_poodle_level_7
import com.project.giunne.character_rabbit_level_1
import com.project.giunne.character_rabbit_level_2
import com.project.giunne.character_rabbit_level_3
import com.project.giunne.character_rabbit_level_4
import com.project.giunne.character_rabbit_level_5
import com.project.giunne.character_rabbit_level_6
import com.project.giunne.character_rabbit_level_7
import com.project.giunne.character_raccoon_level_1
import com.project.giunne.character_raccoon_level_2
import com.project.giunne.character_raccoon_level_3
import com.project.giunne.character_raccoon_level_4
import com.project.giunne.character_raccoon_level_5
import com.project.giunne.character_raccoon_level_6
import com.project.giunne.character_raccoon_level_7
import com.project.giunne.character_rop_rabbit_level_1
import com.project.giunne.character_rop_rabbit_level_2
import com.project.giunne.character_rop_rabbit_level_3
import com.project.giunne.character_rop_rabbit_level_4
import com.project.giunne.character_rop_rabbit_level_5
import com.project.giunne.character_rop_rabbit_level_6
import com.project.giunne.character_rop_rabbit_level_7
import com.project.giunne.character_tiger_level_1
import com.project.giunne.character_tiger_level_2
import com.project.giunne.character_tiger_level_3
import com.project.giunne.character_tiger_level_4
import com.project.giunne.character_tiger_level_5
import com.project.giunne.character_tiger_level_6
import com.project.giunne.character_tiger_level_7
import org.jetbrains.compose.resources.DrawableResource

val avatarList = listOf(
    Avatar(Animal.CharacterBearLevel1, "노얼굴", 1),
    Avatar(Animal.CharacterBearLevel5, "곰퉁이", 5),
    Avatar(Animal.CharacterCatLevel7, "고냥", 7),
    Avatar(Animal.CharacterDinoLevel1, "띠라노", 1),
    Avatar(Animal.CharacterDuckLevel3, "오오리", 3),
    Avatar(Animal.CharacterFoxLevel3, "여웅", 3),
    Avatar(Animal.CharacterPoodleLevel4, "푸들", 4),
    Avatar(Animal.CharacterRaccoonLevel6, "너굴맨", 6),
    Avatar(Animal.CharacterTigerLevel4, "호랭", 4),
)

data class Avatar(
    val avatar: Animal,
    val name: String,
    val level: Int,
)

sealed class Animal(val img: DrawableResource) {
    data object CharacterBearLevel1: Animal(Res.drawable.character_bear_level_1)
    data object CharacterBearLevel2: Animal(Res.drawable.character_bear_level_2)
    data object CharacterBearLevel3: Animal(Res.drawable.character_bear_level_3)
    data object CharacterBearLevel4: Animal(Res.drawable.character_bear_level_4)
    data object CharacterBearLevel5: Animal(Res.drawable.character_bear_level_5)
    data object CharacterBearLevel6: Animal(Res.drawable.character_bear_level_6)
    data object CharacterBearLevel7: Animal(Res.drawable.character_bear_level_7)
    data object CharacterCatLevel1: Animal(Res.drawable.character_cat_level_1)
    data object CharacterCatLevel2: Animal(Res.drawable.character_cat_level_2)
    data object CharacterCatLevel3: Animal(Res.drawable.character_cat_level_3)
    data object CharacterCatLevel4: Animal(Res.drawable.character_cat_level_4)
    data object CharacterCatLevel5: Animal(Res.drawable.character_cat_level_5)
    data object CharacterCatLevel6: Animal(Res.drawable.character_cat_level_6)
    data object CharacterCatLevel7: Animal(Res.drawable.character_cat_level_7)
    data object CharacterCorgiLevel1: Animal(Res.drawable.character_corgi_level_1)
    data object CharacterCorgiLevel2: Animal(Res.drawable.character_corgi_level_2)
    data object CharacterCorgiLevel3: Animal(Res.drawable.character_corgi_level_3)
    data object CharacterCorgiLevel4: Animal(Res.drawable.character_corgi_level_4)
    data object CharacterCorgiLevel5: Animal(Res.drawable.character_corgi_level_5)
    data object CharacterCorgiLevel6: Animal(Res.drawable.character_corgi_level_6)
    data object CharacterCorgiLevel7: Animal(Res.drawable.character_corgi_level_7)
    data object CharacterDinoLevel1: Animal(Res.drawable.character_dino_level_1)
    data object CharacterDinoLevel2: Animal(Res.drawable.character_dino_level_2)
    data object CharacterDinoLevel3: Animal(Res.drawable.character_dino_level_3)
    data object CharacterDinoLevel4: Animal(Res.drawable.character_dino_level_4)
    data object CharacterDinoLevel5: Animal(Res.drawable.character_dino_level_5)
    data object CharacterDinoLevel6: Animal(Res.drawable.character_dino_level_6)
    data object CharacterDinoLevel7: Animal(Res.drawable.character_dino_level_7)
    data object CharacterDuckLevel1: Animal(Res.drawable.character_duck_level_1)
    data object CharacterDuckLevel2: Animal(Res.drawable.character_duck_level_2)
    data object CharacterDuckLevel3: Animal(Res.drawable.character_duck_level_3)
    data object CharacterDuckLevel4: Animal(Res.drawable.character_duck_level_4)
    data object CharacterDuckLevel5: Animal(Res.drawable.character_duck_level_5)
    data object CharacterDuckLevel6: Animal(Res.drawable.character_duck_level_6)
    data object CharacterDuckLevel7: Animal(Res.drawable.character_duck_level_7)
    data object CharacterFoxLevel1: Animal(Res.drawable.character_fox_level_1)
    data object CharacterFoxLevel2: Animal(Res.drawable.character_fox_level_2)
    data object CharacterFoxLevel3: Animal(Res.drawable.character_fox_level_3)
    data object CharacterFoxLevel4: Animal(Res.drawable.character_fox_level_4)
    data object CharacterFoxLevel5: Animal(Res.drawable.character_fox_level_5)
    data object CharacterFoxLevel6: Animal(Res.drawable.character_fox_level_6)
    data object CharacterFoxLevel7: Animal(Res.drawable.character_fox_level_7)
    data object CharacterPenguinLevel1: Animal(Res.drawable.character_penguin_level_1)
    data object CharacterPenguinLevel2: Animal(Res.drawable.character_penguin_level_2)
    data object CharacterPenguinLevel3: Animal(Res.drawable.character_penguin_level_3)
    data object CharacterPenguinLevel4: Animal(Res.drawable.character_penguin_level_4)
    data object CharacterPenguinLevel5: Animal(Res.drawable.character_penguin_level_5)
    data object CharacterPenguinLevel6: Animal(Res.drawable.character_penguin_level_6)
    data object CharacterPenguinLevel7: Animal(Res.drawable.character_penguin_level_7)
    data object CharacterPoodleLevel1: Animal(Res.drawable.character_poodle_level_1)
    data object CharacterPoodleLevel2: Animal(Res.drawable.character_poodle_level_2)
    data object CharacterPoodleLevel3: Animal(Res.drawable.character_poodle_level_3)
    data object CharacterPoodleLevel4: Animal(Res.drawable.character_poodle_level_4)
    data object CharacterPoodleLevel5: Animal(Res.drawable.character_poodle_level_5)
    data object CharacterPoodleLevel6: Animal(Res.drawable.character_poodle_level_6)
    data object CharacterPoodleLevel7: Animal(Res.drawable.character_poodle_level_7)
    data object CharacterRabbitLevel1: Animal(Res.drawable.character_rabbit_level_1)
    data object CharacterRabbitLevel2: Animal(Res.drawable.character_rabbit_level_2)
    data object CharacterRabbitLevel3: Animal(Res.drawable.character_rabbit_level_3)
    data object CharacterRabbitLevel4: Animal(Res.drawable.character_rabbit_level_4)
    data object CharacterRabbitLevel5: Animal(Res.drawable.character_rabbit_level_5)
    data object CharacterRabbitLevel6: Animal(Res.drawable.character_rabbit_level_6)
    data object CharacterRabbitLevel7: Animal(Res.drawable.character_rabbit_level_7)
    data object CharacterRaccoonLevel1: Animal(Res.drawable.character_raccoon_level_1)
    data object CharacterRaccoonLevel2: Animal(Res.drawable.character_raccoon_level_2)
    data object CharacterRaccoonLevel3: Animal(Res.drawable.character_raccoon_level_3)
    data object CharacterRaccoonLevel4: Animal(Res.drawable.character_raccoon_level_4)
    data object CharacterRaccoonLevel5: Animal(Res.drawable.character_raccoon_level_5)
    data object CharacterRaccoonLevel6: Animal(Res.drawable.character_raccoon_level_6)
    data object CharacterRaccoonLevel7: Animal(Res.drawable.character_raccoon_level_7)
    data object CharacterRopRabbitLevel1: Animal(Res.drawable.character_rop_rabbit_level_1)
    data object CharacterRopRabbitLevel2: Animal(Res.drawable.character_rop_rabbit_level_2)
    data object CharacterRopRabbitLevel3: Animal(Res.drawable.character_rop_rabbit_level_3)
    data object CharacterRopRabbitLevel4: Animal(Res.drawable.character_rop_rabbit_level_4)
    data object CharacterRopRabbitLevel5: Animal(Res.drawable.character_rop_rabbit_level_5)
    data object CharacterRopRabbitLevel6: Animal(Res.drawable.character_rop_rabbit_level_6)
    data object CharacterRopRabbitLevel7: Animal(Res.drawable.character_rop_rabbit_level_7)
    data object CharacterTigerLevel1: Animal(Res.drawable.character_tiger_level_1)
    data object CharacterTigerLevel2: Animal(Res.drawable.character_tiger_level_2)
    data object CharacterTigerLevel3: Animal(Res.drawable.character_tiger_level_3)
    data object CharacterTigerLevel4: Animal(Res.drawable.character_tiger_level_4)
    data object CharacterTigerLevel5: Animal(Res.drawable.character_tiger_level_5)
    data object CharacterTigerLevel6: Animal(Res.drawable.character_tiger_level_6)
    data object CharacterTigerLevel7: Animal(Res.drawable.character_tiger_level_7)
}