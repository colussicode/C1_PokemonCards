package com.example.c1_pokemoncards.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.c1_pokemoncards.data.models.Pokemon

@Dao
interface PokemonDao {
    @Insert
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    suspend fun getAll() : List<PokemonEntity>
}