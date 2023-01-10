package com.example.c1_pokemoncards.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.c1_pokemoncards.R
import com.example.c1_pokemoncards.data.models.Pokemon
import com.example.c1_pokemoncards.data.models.PokemonResult
import com.example.c1_pokemoncards.data.remote.RetrofitService
import com.example.c1_pokemoncards.data.remote.service.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val service: PokemonService = RetrofitService.getInstance().create(PokemonService::class.java)
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var pokemonRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(service))[HomeViewModel::class.java]
        initWidgets()
        setObservables()

    }

    private fun initWidgets() {
        pokemonRecyclerView = findViewById(R.id.rv_pokemon)
    }

    private fun setObservables() {
        homeViewModel.uiState.observe(this) { uiState ->
            when(uiState) {
                is UiState.Resume -> pokemonRecyclerView.adapter = PokemonListAdapter(uiState.pokemonList)
                is UiState.Error -> uiState.error
                else -> println("Erro")
            }
        }
    }

}