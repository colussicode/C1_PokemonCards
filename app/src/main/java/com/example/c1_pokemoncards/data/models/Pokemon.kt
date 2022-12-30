package com.example.c1_pokemoncards.data.models

data class Pokemon(
    val result: List<PokemonObject>
)

data class PokemonObject(
    val id: String,
    val name: String,
)

data class PokemonResult(
    val cards: List<Pokemon>
)