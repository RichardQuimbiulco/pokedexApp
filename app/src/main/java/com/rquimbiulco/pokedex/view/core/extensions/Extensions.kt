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
        PokemonType.NORMAL -> Color(0xFFF2F2F2)
        PokemonType.FIRE -> Color(0xFFFFE5E5)
        PokemonType.WATER -> Color(0xFFE3F2FD)
        PokemonType.ELECTRIC -> Color(0xFFFFF8D6)
        PokemonType.GRASS -> Color(0xFFE8F5E9)
        PokemonType.ICE -> Color(0xFFE0F7FA)
        PokemonType.FIGHTING -> Color(0xFFFFE0E0)
        PokemonType.POISON -> Color(0xFFF3E5F5)
        PokemonType.GROUND -> Color(0xFFF3E8D0)
        PokemonType.FLYING -> Color(0xFFEDE7F6)
        PokemonType.PSYCHIC -> Color(0xFFFFE4EC)
        PokemonType.BUG -> Color(0xFFF1F8E9)
        PokemonType.ROCK -> Color(0xFFEFEBE0)
        PokemonType.GHOST -> Color(0xFFE8EAF6)
        PokemonType.DRAGON -> Color(0xFFE8E0F7)
        PokemonType.DARK -> Color(0xFFE5E0E8)
        PokemonType.STEEL -> Color(0xFFE8EAF0)
        PokemonType.FAIRY -> Color(0xFFFFE8F0)
    }