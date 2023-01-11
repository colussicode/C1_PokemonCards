package com.example.c1_pokemoncards.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons", indices = [Index(value = ["id"], unique = true)])
data class PokemonEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "set_code") val setCode: String,
    @ColumnInfo(name = "number") val number: String
)
