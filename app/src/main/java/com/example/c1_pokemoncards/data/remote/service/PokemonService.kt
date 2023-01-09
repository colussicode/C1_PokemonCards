package com.example.c1_pokemoncards.data.remote.service

import com.example.c1_pokemoncards.data.models.PokemonResult
import retrofit2.http.GET

interface PokemonService {
    @GET("cards")
    suspend fun getData(): PokemonResult
}