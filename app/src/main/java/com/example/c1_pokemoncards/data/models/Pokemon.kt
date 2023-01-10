package com.example.c1_pokemoncards.data.models

data class Pokemon(
    val id: String,
    val name: String,
    val imgUrl: String,
    val imageUrlHiRes: String,
    val setCode: String,
    val number: String
)

data class PokemonResult(
    val cards: List<Pokemon>
)