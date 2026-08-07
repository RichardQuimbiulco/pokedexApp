package com.rquimbiulco.pokedex.util

private const val IMAGE_BASE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

fun String.extractPokemonId(): Int {
    return trimEnd('/')
        .substringAfterLast('/')
        .toInt()
}

fun Int.toPokemonImageUrl(): String {
    return "$IMAGE_BASE_URL$this.png"
}