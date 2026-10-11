package com.rquimbiulco.pokedex.view.core.extensions

import androidx.compose.ui.graphics.Color
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.details.PokemonType

fun PokemonType.iconRes(): Int =
    when (this) {
        PokemonType.NORMAL -> R.drawable.ic_type_normal
        PokemonType.FIRE -> R.drawable.ic_type_fire
        PokemonType.WATER -> R.drawable.ic_type_water
        PokemonType.ELECTRIC -> R.drawable.ic_type_electric
        PokemonType.GRASS -> R.drawable.ic_type_grass
        PokemonType.ICE -> R.drawable.ic_type_ice
        PokemonType.FIGHTING -> R.drawable.ic_type_fighting
        PokemonType.POISON -> R.drawable.ic_type_poison
        PokemonType.FAIRY -> R.drawable.ic_type_fairy
        PokemonType.GROUND -> R.drawable.ic_type_ground
        PokemonType.FLYING -> R.drawable.ic_type_flying
        PokemonType.PSYCHIC -> R.drawable.ic_type_psychic
        PokemonType.BUG -> R.drawable.ic_type_bug
        PokemonType.ROCK -> R.drawable.ic_type_rock
        PokemonType.GHOST -> R.drawable.ic_type_ghost
        PokemonType.DRAGON -> R.drawable.ic_type_dragon
        PokemonType.DARK -> R.drawable.ic_type_dark
        PokemonType.STEEL -> R.drawable.ic_type_steel
    }

fun PokemonType.backgroundColor(): Color =
    when (this) {
        PokemonType.NORMAL -> Color(0xFFE4E4E4)
        PokemonType.FIRE -> Color(0xFFFFC7C7)
        PokemonType.WATER -> Color(0xFFBBDDF7)
        PokemonType.ELECTRIC -> Color(0xFFFFE68A)
        PokemonType.GRASS -> Color(0xFFC8E6C9)
        PokemonType.ICE -> Color(0xFFBDECF2)
        PokemonType.FIGHTING -> Color(0xFFFFBABA)
        PokemonType.POISON -> Color(0xFFDDBBE3)
        PokemonType.GROUND -> Color(0xFFE5CFA3)
        PokemonType.FLYING -> Color(0xFFD5C8EF)
        PokemonType.PSYCHIC -> Color(0xFFFFC4D6)
        PokemonType.BUG -> Color(0xFFD6EABF)
        PokemonType.ROCK -> Color(0xFFD8CEB5)
        PokemonType.GHOST -> Color(0xFFC9CDED)
        PokemonType.DRAGON -> Color(0xFFD1C1EE)
        PokemonType.DARK -> Color(0xFFC9C2D0)
        PokemonType.STEEL -> Color(0xFFD0D3DC)
        PokemonType.FAIRY -> Color(0xFFFFC9DD)
    }