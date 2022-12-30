package com.example.c1_pokemoncards.data.models

data class Pokemon(
    val id: String,
    val name: String
)

data class PokemonResult(
    val cards: List<Pokemon>
)