package com.example.c1_pokemoncards.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : androidx.room.RoomDatabase(){
    abstract fun pokemonDao() : PokemonDao

    companion object {

        @Volatile
        private var instance: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): PokemonDatabase {
            return Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemondb")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}