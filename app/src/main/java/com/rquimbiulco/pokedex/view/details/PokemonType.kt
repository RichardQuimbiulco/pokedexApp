package com.rquimbiulco.pokedex.view.details

enum class PokemonType(val id: Int, val value: String) {
    NORMAL(1, "normal"),
    FIRE(2, "fire"),
    WATER(3, "water"),
    ELECTRIC(4, "electric"),
    GRASS(5, "grass"),
    ICE(6, "ice"),
    FIGHTING(7, "fighting"),
    POISON(8, "poison"),
    GROUND(9, "ground"),
    FLYING(10, "flying"),
    PSYCHIC(11, "psychic"),
    BUG(12, "bug"),
    ROCK(13, "rock"),
    GHOST(14, "ghost"),
    DRAGON(15, "dragon"),
    DARK(16, "dark"),
    STEEL(17, "steel"),
    FAIRY(18, "fairy");

    companion object {
        fun fromId(id: Int): PokemonType =
            entries.first { it.id == id }
    }
}